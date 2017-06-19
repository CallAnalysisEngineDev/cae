package org.cae.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.cae.common.Condition;
import org.cae.common.DaoResult;
import org.cae.common.IConstant;
import org.cae.common.SqlWithParams;
import static org.cae.common.Util.*;
import org.cae.dao.ICallDao;
import org.cae.entity.CallRecord;
import org.cae.entity.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("callDao")
public class CallDaoImpl implements ICallDao {

	private Logger logger=Logger.getLogger(this.getClass());
	@Autowired
	private JdbcTemplate template;
	
	@Override
	public DaoResult getAllCallDao(Condition condition, CallRecord callRecord) {
		String sql="";
		List<CallRecord> theResult=null;
		try{
			sql="SELECT cr.call_id,cr.call_version "
					+ "FROM call_record AS cr "
					+ "LEFT JOIN song AS s "
					+ "USING(song_id) "
					+ "WHERE s.song_id = ? "
					+ "ORDER BY cr.call_version DESC "
					+ "LIMIT "+condition.getPageStart()+","+condition.getPageLimit();
			theResult=template.query(sql, new Object[]{callRecord.getSong().getSongId()}, new RowMapper<CallRecord>(){
				@Override
				public CallRecord mapRow(ResultSet rs, int row)
						throws SQLException {
					CallRecord callRecord=new CallRecord();
					callRecord.setCallId(rs.getString("call_id"));
					callRecord.setCallVersion(rs.getShort("call_version"));
					return callRecord;
				}
			});
			return new DaoResult(true, theResult);
		}catch(Exception ex){
			logStackTrace(logger, ex.getStackTrace());
			ex.printStackTrace();
			return null;
		}
	}
	
	@Override
	public DaoResult getCallCountDao(Condition condition, CallRecord callRecord) {
		String sql="SELECT COUNT(*) "
				+ "FROM call_record AS cr "
				+ "LEFT JOIN song AS s "
				+ "USING(song_id)"
				+ "WHERE s.song_id = ? ";
		Integer theResult=template.queryForObject(sql, new Object[]{callRecord.getSong().getSongId()}, Integer.class);
		return new DaoResult(true, theResult);
	}

	@Override
	public DaoResult getCallDao(CallRecord callRecord){
		String sql="";
		CallRecord theResult=null;
		SqlWithParams sqlWithParams=getTheSqlForGet(callRecord);
		try{
			//根据歌曲id来获取该歌曲最新的call表记录
			sql="SELECT s.song_name,s.song_owner,cr.call_source,song_sell_time,cr.call_version,s.song_last_modify_time,s.song_cover,s.song_id,s.song_video "
					+ "FROM call_record AS cr "
					+ "LEFT JOIN song AS s "
					+ "USING(song_id) "
					+ sqlWithParams.getWhere()
					+ "ORDER BY cr.call_version DESC "
					+ "LIMIT 1";
			theResult=template.queryForObject(sql, sqlWithParams.getParams(), new RowMapper<CallRecord>(){

				@Override
				public CallRecord mapRow(ResultSet rs, int row)
						throws SQLException {
					CallRecord callRecord=new CallRecord();
					callRecord.setCallSource(IConstant.STATIC_PREFIX+rs.getString("call_source"));
					callRecord.setCallVersion(rs.getShort("call_version"));
					Song song=new Song();
					song.setSongName(rs.getString("song_name"));
					song.setSongOwner(rs.getString("song_owner"));
					song.setSongSellTime(date2String(rs.getDate("song_sell_time")));
					song.setSongLastModifyTime(date2String(rs.getDate("song_last_modify_time")));
					song.setSongCover(IConstant.STATIC_PREFIX+rs.getString("song_cover"));
					song.setSongId(rs.getString("song_id"));
					song.setSongVideo(rs.getShort("song_video"));
					callRecord.setSong(song);
					return callRecord;
				}
				
			});
		}catch(EmptyResultDataAccessException ex){
			logger.info("当前歌曲"+callRecord.getSong().getSongId()+"不存在call表");
			ex.printStackTrace();
			return new DaoResult(false, "当前歌曲"+callRecord.getSong().getSongId()+"不存在call表");
		}catch(Exception ex){
			logStackTrace(logger, ex.getStackTrace());
			ex.printStackTrace();
			return new DaoResult(false, ex.getMessage());
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
		return new DaoResult(true, theResult);
	}
	
	private SqlWithParams getTheSqlForGet(CallRecord callRecord){
		StringBuffer buffer=new StringBuffer();
		int insertIndex;
		Object[] preParams=new Object[1];
		int paramsIndex=0;
		buffer.append("WHERE 1=1 ");
		
		if(callRecord.getSong()!=null&&isNotNull(callRecord.getSong().getSongId())){
			//这是获取最新的call表的情况,即通过songId来获取
			insertIndex=buffer.indexOf("WHERE")+5;
			buffer.insert(insertIndex, " s.song_id = ? AND ");//拼接where
			preParams[paramsIndex]=callRecord.getSong().getSongId();
			paramsIndex++;
		}
		else if(isNotNull(callRecord.getCallId())){
			//这是获取某个旧版本的call表的情况,即通过callId来获取
			insertIndex=buffer.indexOf("WHERE")+5;
			buffer.insert(insertIndex, " cr.call_id = ? AND ");
			preParams[paramsIndex]=callRecord.getCallId();
			paramsIndex++;
		}
		
		Object[] params=new Object[paramsIndex];
		System.arraycopy(preParams, 0, params, 0, paramsIndex);
		return new SqlWithParams(buffer.toString(),params);
	}

	@Override
	public DaoResult saveCallDao(CallRecord callRecord) {
		try{
			//添加一个call表的记录
			String sql="INSERT INTO call_record(call_id,song_id,call_source,call_version) "
					+ "VALUES(?,?,?,?)";
			template.update(sql, getCharId("CR-", 10),
					callRecord.getSong().getSongId(),
					callRecord.getCallSource(),
					callRecord.getCallVersion());
			logger.info("插入新的call表记录成功");
			sql="UPDATE song "
				+ "SET song_last_modify_time = ? "
				+ "WHERE song_id = ?";
			template.update(sql, getNowDate(),callRecord.getSong().getSongId());
			logger.info("更新歌曲最后修改时间成功");
			return new DaoResult(true, null);
		}catch(Exception ex){
			logStackTrace(logger, ex.getStackTrace());
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
			logStackTrace(logger, ex.getStackTrace());
			ex.printStackTrace();
			return new DaoResult(false, "删除失败");
		}
	}

	@Override
	public DaoResult deleteCallDao(final List<CallRecord> callRecords) {
		try{
			//删除多个call表的记录
			String sql="DELETE FROM call_record "
					+ "WHERE call_id IN (";
			for(int i=0;i<callRecords.size();i++){
				if(i==callRecords.size()-1){
					sql+="?)";
				}
				else{
					sql+="?,";
				}
			}
			PreparedStatementSetter setter=new PreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					for(int i=1;i<=callRecords.size();i++){
						ps.setString(i, callRecords.get(i-1).getCallId());
					}
				}
			};
			template.update(sql, setter);
			logger.info("批删除call表记录成功");
			return new DaoResult(true, null);
		}catch(Exception ex){
			logStackTrace(logger, ex.getStackTrace());
			ex.printStackTrace();
			return new DaoResult(false, "批删除失败");
		}
	}

	@Override
	public Logger getLogger() {
		return logger;
	}
	
}
