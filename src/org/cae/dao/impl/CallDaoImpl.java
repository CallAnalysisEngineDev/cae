package org.cae.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.cae.common.Condition;
import org.cae.common.DaoResult;
import static org.cae.common.Util.*;
import org.cae.dao.ICallDao;
import org.cae.object.dto.CallRecord;
import org.cae.object.dto.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
	private KumaSqlDao kumaSqlDao;

	@Override
	public DaoResult getAllCallDao(Condition condition, CallRecord callRecord) {
		kumaSqlDao.selectMode();
		ListSqlResult theResult = kumaSqlDao.select("call_id", "cr").select("call_version", "cr")
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
		kumaSqlDao.selectMode();
		IntegerSqlResult theResult = kumaSqlDao.select(SelectClause.COUNT).from(CallRecord.class, "cr")
				.leftjoin(Song.class, "s", "song_id", CallRecord.class, "song_id")
				.where("song_id", "s", WhereType.EQUALS, callRecord.getSong().getSongId()).endForNumber();
		return new DaoResult(true, theResult.getResult());
	}

	@Override
	public DaoResult getCallDao(CallRecord callRecord) {
		kumaSqlDao.selectMode();
		KumaSqlDao temp = kumaSqlDao.select("song_name", "s").select("song_owner", "s").select("call_source", "cr")
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
		kumaSqlDao.updateMode();
		kumaSqlDao.update("song_click", UpdateSetType.ADD, 1).table(Song.class)
				.where("song_id", WhereType.EQUALS, theResult.getResult().getSong().getSongId()).end();
		return new DaoResult(true, theResult.getResult());
	}

	@Override
	public DaoResult saveCallDao(CallRecord callRecord) {
		kumaSqlDao.insertMode();
		kumaSqlDao.insert("call_id", getCharId("CR-", 10)).insert("song_id", callRecord.getSong().getSongId())
				.insert("call_source", callRecord.getCallSource()).insert("call_version", callRecord.getCallVersion())
				.table(CallRecord.class).end();
		kumaSqlDao.updateMode();
		kumaSqlDao.update("song_last_modify_time", UpdateSetType.ASSIGN, getNowDate()).table(Song.class)
				.where("song_id", WhereType.EQUALS, callRecord.getSong().getSongId()).end();
		return new DaoResult(true);
	}

	@Override
	public DaoResult deleteCallDao(CallRecord callRecord) {
		kumaSqlDao.deleteMode();
		kumaSqlDao.table(CallRecord.class).where("call_id", WhereType.EQUALS, callRecord.getCallId()).end();
		return new DaoResult(true);
	}

	@Override
	public DaoResult deleteCallDao(final List<CallRecord> callRecords) {
		kumaSqlDao.deleteMode();
		List<Object> params = new ArrayList<>();
		for (CallRecord callRecord : callRecords) {
			params.add(callRecord.getCallId());
		}
		kumaSqlDao.table(CallRecord.class).where("call_id", WhereType.IN, params).end();
		return new DaoResult(true);
	}

	@Override
	public Logger getLogger() {
		return logger;
	}

}
