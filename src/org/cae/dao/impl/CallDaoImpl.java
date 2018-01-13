package org.cae.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.cae.common.Condition;
import org.cae.common.DaoResult;
import org.cae.common.SqlWithParams;
import static org.cae.common.Util.*;
import org.cae.dao.ICallDao;
import org.cae.object.dto.CallRecord;
import org.cae.object.dto.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import top.starrysea.kql.clause.OrderByType;
import top.starrysea.kql.clause.SelectClause;
import top.starrysea.kql.clause.UpdateSetType;
import top.starrysea.kql.clause.WhereType;
import top.starrysea.kql.facede.EntitySqlResult;
import top.starrysea.kql.facede.IntegerSqlResult;
import top.starrysea.kql.facede.KumaSqlDao;
import top.starrysea.kql.facede.ListSqlResult;

import static org.cae.common.IConstant.*;

@Repository("callDao")
public class CallDaoImpl implements ICallDao {

	private Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private JdbcTemplate template;
	@Autowired
	private KumaSqlDao KumaSqlDao;

	@Override
	public DaoResult getAllCallDao(Condition condition, CallRecord callRecord) {
		KumaSqlDao.selectMode();
		ListSqlResult theResult = KumaSqlDao.select("call_id", "cr").select("call_version", "cr")
				.from(CallRecord.class, "cr").leftjoin(Song.class, "s", "song_id", CallRecord.class, "song_id")
				.where("song_id", "s", WhereType.EQUALS, callRecord.getSong().getSongId())
				.orderBy("call_version", "cr", OrderByType.DESC)
				.limit(condition.getPageStart(), condition.getPageLimit())
				.endForList((rs, row) -> new CallRecord.Builder().callId(rs.getString("call_id"))
						.callVersion(rs.getShort("call_version")).build());
		return new DaoResult(true, theResult.getResult());
	}

	@Override
	public DaoResult getCallCountDao(Condition condition, CallRecord callRecord) {
		KumaSqlDao.selectMode();
		IntegerSqlResult theResult = KumaSqlDao.select(SelectClause.COUNT).from(CallRecord.class, "cr")
				.leftjoin(Song.class, "s", "song_id", CallRecord.class, "song_id")
				.where("song_id", "s", WhereType.EQUALS, callRecord.getSong().getSongId()).endForNumber();
		return new DaoResult(true, theResult.getResult());
	}

	@Override
	public DaoResult getCallDao(CallRecord callRecord) {
		KumaSqlDao.selectMode();
		KumaSqlDao temp = KumaSqlDao.select("song_name", "s").select("song_owner", "s").select("call_source", "cr")
				.select("song_sell_time", "s").select("call_version", "cr").select("song_last_modify_time", "s")
				.select("song_cover", "s").select("song_id", "s").select("song_video", "s").from(CallRecord.class, "cr")
				.leftjoin(Song.class, "s", "song_id", CallRecord.class, "song_id");
		if (isNotNull(callRecord.getSong().getSongId())) {
			temp.where("song_id", "s", WhereType.EQUALS, callRecord.getSong().getSongId());
		} else if (isNotNull(callRecord.getCallId())) {
			temp.where("call_id", "cr", WhereType.EQUALS, callRecord.getCallId());
		}
		EntitySqlResult<CallRecord> theResult;
		try {
			theResult = temp.orderBy("call_version", "cr", OrderByType.DESC).limit(1)
					.endForObject((rs, row) -> new CallRecord.Builder()
							.callSource(STATIC_PREFIX + rs.getString("call_source"))
							.callVersion(rs.getShort("call_version"))
							.song(new Song.Builder().songName(rs.getString("song_name"))
									.songOwner(rs.getString("song_owner"))
									.songSellTime(date2String(rs.getDate("song_sell_time")))
									.songLastModifyTime(date2String(rs.getDate("song_last_modify_time")))
									.songCover(STATIC_PREFIX + rs.getString("song_cover"))
									.songId(rs.getString("song_id")).songVideo(rs.getShort("song_video")).build())
							.build());
		} catch (EmptyResultDataAccessException ex) {
			logger.info("当前歌曲" + callRecord.getSong().getSongId() + "不存在call表", ex);
			return new DaoResult(true, "当前歌曲" + callRecord.getSong().getSongId() + "不存在call表");
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			return new DaoResult(false, ex.getMessage());
		}
		KumaSqlDao.updateMode();
		KumaSqlDao.update("song_click", UpdateSetType.ADD, 1)
				.where("song_id", WhereType.EQUALS, theResult.getResult().getSong().getSongId()).end();
		return new DaoResult(true, theResult.getResult());
	}

	@Override
	public DaoResult saveCallDao(CallRecord callRecord) {
		try {
			// 添加一个call表的记录
			String sql = "INSERT INTO call_record(call_id,song_id,call_source,call_version) " + "VALUES(?,?,?,?)";
			template.update(sql, getCharId("CR-", 10), callRecord.getSong().getSongId(), callRecord.getCallSource(),
					callRecord.getCallVersion());
			logger.info("插入新的call表记录成功");
			sql = "UPDATE song " + "SET song_last_modify_time = ? " + "WHERE song_id = ?";
			template.update(sql, getNowDate(), callRecord.getSong().getSongId());
			logger.info("更新歌曲最后修改时间成功");
			return new DaoResult(true, null);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			return new DaoResult(false, "插入失败");
		}
	}

	@Override
	public DaoResult deleteCallDao(CallRecord callRecord) {
		try {
			// 删除一个call表的记录
			String sql = "DELETE FROM call_record " + "WHERE call_id = ?";
			template.update(sql, callRecord.getCallId());
			logger.info("删除id为" + callRecord.getCallId() + "的call表记录成功");
			return new DaoResult(true, null);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			return new DaoResult(false, "删除失败");
		}
	}

	@Override
	public DaoResult deleteCallDao(final List<CallRecord> callRecords) {
		try {
			// 删除多个call表的记录
			String sql = "DELETE FROM call_record " + "WHERE call_id IN (";
			for (int i = 0; i < callRecords.size(); i++) {
				if (i == callRecords.size() - 1) {
					sql += "?)";
				} else {
					sql += "?,";
				}
			}
			template.update(sql, (ps) -> {
				for (int i = 1; i <= callRecords.size(); i++) {
					ps.setString(i, callRecords.get(i - 1).getCallId());
				}
			});
			logger.info("批删除call表记录成功");
			return new DaoResult(true, null);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			return new DaoResult(false, "批删除失败");
		}
	}

	@Override
	public Logger getLogger() {
		return logger;
	}

}
