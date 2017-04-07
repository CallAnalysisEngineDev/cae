package org.cae.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.cae.common.Condition;
import org.cae.common.DaoResult;
import org.cae.common.Util;
import org.cae.dao.ICallDao;
import org.cae.entity.CallRecord;
import org.cae.entity.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("callDao")
public class CallDaoImpl implements ICallDao {

	private Logger logger=Logger.getLogger(this.getClass());
	@Autowired
	private JdbcTemplate template;
	
	@Override
	public List<CallRecord> getAllCallDao(Condition condition, CallRecord callRecord) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Integer getCallCountDao(Condition condition, CallRecord callRecord) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CallRecord getCallDao(CallRecord callRecord){
		
		String sql="";
		CallRecord theResult=null;
		try{
			//根据歌曲id来获取该歌曲最新的call表记录
			sql="SELECT s.song_name,s.song_owner,cr.call_source,song_sell_time,cr.call_version,s.song_last_modify_time,s.song_cover,s.song_id,s.song_video "
					+ "FROM call_record AS cr "
					+ "LEFT JOIN song AS s "
					+ "USING(song_id) "
					+ "WHERE s.song_id = ? "
					+ "ORDER BY cr.call_version DESC "
					+ "LIMIT 1";
			theResult=template.queryForObject(sql, new Object[]{callRecord.getSong().getSongId()}, new RowMapper<CallRecord>(){

				@Override
				public CallRecord mapRow(ResultSet rs, int row)
						throws SQLException {
					CallRecord callRecord=new CallRecord();
					callRecord.setCallSource(rs.getString("call_source"));
					callRecord.setCallVersion(rs.getShort("call_version"));
					Song song=new Song();
					song.setSongName(rs.getString("song_name"));
					song.setSongOwner(rs.getString("song_owner"));
					song.setSongSellTime(Util.date2String(rs.getDate("song_sell_time")));
					song.setSongLastModifyTime(Util.date2String(rs.getDate("song_last_modify_time")));
					song.setSongCover(rs.getString("song_cover"));
					song.setSongId(rs.getString("song_id"));
					song.setSongVideo(rs.getShort("song_video"));
					callRecord.setSong(song);
					return callRecord;
				}
				
			});
		}catch(EmptyResultDataAccessException ex){
			logger.info("当前歌曲"+callRecord.getSong().getSongId()+"不存在call表");
			ex.printStackTrace();
			return null;
		}catch(Exception ex){
			logger.error(ex.getMessage());
			ex.printStackTrace();
			return null;
		}
		try{
			//该歌曲的点击量+1
			sql="UPDATE song "
					+ "SET song_click = song_click + 1 "
					+ "WHERE song_id = ?";
			template.update(sql, theResult.getSong().getSongId());
		}catch(Exception ex){
			ex.printStackTrace();
			logger.warn("增加歌曲id为"+theResult.getSong().getSongId()+"的歌曲的点击量失败");
		}
		return theResult;
	}

	@Override
	public DaoResult saveCallDao(CallRecord callRecord) {
		try{
			//添加一个call表的记录
			String sql="INSERT INTO call_record(call_id,song_id,call_source,call_version) "
					+ "VALUES(?,?,?,?)";
			template.update(sql, Util.getCharId("CR-", 10),
					callRecord.getSong().getSongId(),
					callRecord.getCallSource(),
					callRecord.getCallVersion());
			logger.info("插入新的call表记录成功");
			return new DaoResult(true, null);
		}catch(Exception ex){
			logger.error(ex.getMessage());
			ex.printStackTrace();
			return new DaoResult(false, "插入失败");
		}
	}

	@Override
	public DaoResult deleteCallDao(CallRecord callRecord) {
		try{
			//删除一个call表的记录
			String sql="DELETE FROM call_record "
					+ "WHERE call_id = ?";
			template.update(sql, callRecord.getCallId());
			logger.info("删除id为"+callRecord.getCallId()+"的call表记录成功");
			return new DaoResult(true, null);
		}catch(Exception ex){
			logger.error(ex.getMessage());
			ex.printStackTrace();
			return new DaoResult(false, "删除失败");
		}
	}

	@Override
	public DaoResult deleteCallDao(List<CallRecord> callRecords) {
		try{
			//删除多个call表的记录
			String sql="DELETE FROM call_record "
					+ "WHERE call_id = ?";
			List<Object[]> batchArgs=new ArrayList<Object[]>();
			for(int i=0;i<callRecords.size();i++){
				Object[] objectArray=new Object[1];
				objectArray[0]=callRecords.get(i).getCallId();
				batchArgs.add(objectArray);
			}
			//使用batch来批处理,提高性能
			template.batchUpdate(sql, batchArgs);
			logger.info("批删除call表记录成功");
			return new DaoResult(true, null);
		}catch(Exception ex){
			logger.error(ex.getMessage());
			ex.printStackTrace();
			return new DaoResult(false, "批删除失败");
		}
	}

	@Override
	public Logger getLogger() {
		return logger;
	}
	
}
