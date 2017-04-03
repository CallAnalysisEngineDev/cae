package org.cae.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.cae.common.Condition;
import org.cae.common.DaoResult;
import org.cae.common.IConstant;
import org.cae.common.SqlWithParams;
import org.cae.common.Util;
import org.cae.dao.ICallDao;
import org.cae.entity.CallRecord;
import org.cae.entity.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("callDao")
public class CallDaoImpl implements ICallDao {

	private Logger logger=Logger.getLogger(this.getClass().getName());
	
	@Autowired
	private JdbcTemplate template;

	@Override
	public Map<String, Object> getCallForHomepageDao() {
		Map<String,Object> theResult=new HashMap<String,Object>();
		logger.log(Level.INFO, "开始查询热点歌曲名单,当前的限制条数HOMEPAGE_RED_LIMIT="+IConstant.HOMEPAGE_RED_LIMIT);
		String sql="SELECT cr.call_id,s.song_name,s.song_cover,(s.song_click/timestampdiff(hour,s.song_create_time,'"+Util.getNowTime()+"')) AS clickrate "
				+ "FROM call_record AS cr "
				+ "LEFT JOIN song AS s "
				+ "USING(song_id) "
				+ "ORDER BY clickrate DESC "
				+ "LIMIT "+IConstant.HOMEPAGE_RED_LIMIT;
		RowMapper<Map<String,Object>> rowMapper=new RowMapper<Map<String,Object>>() {
			
			@Override
			public Map<String, Object> mapRow(ResultSet rs, int row)
					throws SQLException {
				Map<String, Object> map=new HashMap<String,Object>();
				map.put("callId", rs.getString("call_id"));
				map.put("songName", rs.getString("song_name"));
				map.put("songCover", rs.getString("song_cover"));
				return map;
			}
			
		};
		List<Map<String,Object>> redList=template.query(sql, rowMapper);
		logger.log(Level.INFO, "开始查询最新修改歌曲名单,当前限制条数HOMEPAGE_NEWEST_LIMIT="+IConstant.HOMEPAGE_NEWEST_LIMIT);
		sql="SELECT cr.call_id,s.song_name,s.song_cover "
			+ "FROM call_record AS cr "
			+ "LEFT JOIN song AS s "
			+ "USING(song_id) "
			+ "ORDER BY song_last_modify_time DESC "
			+ "LIMIT "+IConstant.HOMEPAGE_NEWEST_LIMIT;
		List<Map<String,Object>> newestList=template.query(sql, rowMapper);
		logger.log(Level.INFO, "查询完毕,热点歌曲数有"+redList.size()+"条,最新修改歌曲数有"+newestList.size()+"条");
		theResult.put("red", redList);
		theResult.put("newest", newestList);
		return theResult;
	}
	
	@Override
	public List<CallRecord> getAllCallDao(Condition condition, CallRecord callRecord) {
		SqlWithParams sqlWithParams=getTheSqlForGetAll(callRecord);
		String sql="SELECT cr.call_id,s.song_cover,s.song_owner,s.song_name,cr.call_version "
				+ "FROM call_record AS cr "
				+ "LEFT JOIN song AS s "
				+ "USING(song_id) "+sqlWithParams.getSql()
				+ "ORDER BY s.song_last_modify_time DESC "
				+ "LIMIT "+condition.getPageStart()+","+condition.getPageLimit();
		Object[] params=sqlWithParams.getParams();
		List<CallRecord> theResult=template.query(sql, params, new RowMapper<CallRecord>() {

			@Override
			public CallRecord mapRow(ResultSet rs, int row)
					throws SQLException {
				CallRecord callRecord=new CallRecord();
				callRecord.setCallId(rs.getString("call_id"));
				callRecord.setCallVersion(rs.getShort("call_version"));
				Song song=new Song();
				song.setSongCover(rs.getString("song_cover"));
				song.setSongOwner(rs.getString("song_owner"));
				song.setSongName(rs.getString("song_name"));
				callRecord.setSong(song);
				return callRecord;
			}
		});
		return theResult;
	}
	
	private SqlWithParams getTheSqlForGetAll(CallRecord callRecord){
		StringBuffer buffer=new StringBuffer();
		int insertIndex;
		Object[] preParams=new Object[1];
		int paramsIndex=0;
		buffer.append("WHERE 1=1 ");
		
		if(callRecord.getSong()!=null&&Util.isNull(callRecord.getSong().getSongName())){
			insertIndex=buffer.indexOf("WHERE")+5;
			buffer.insert(insertIndex, " s.song_name LIKE ? AND ");
			preParams[paramsIndex]="%"+callRecord.getSong().getSongName()+"%";
			paramsIndex++;
		}
		
		Object[] params=new Object[paramsIndex];
		System.arraycopy(preParams, 0, params, 0, paramsIndex);
		return new SqlWithParams(buffer.toString(),params);
	}
	
	@Override
	public Integer getCallCountDao(Condition condition, CallRecord callRecord) {
		SqlWithParams sqlWithParams=getTheSqlForGetAll(callRecord);
		String sql="SELECT COUNT(*) "
				+ "FROM call_record AS cr "
				+ "LEFT JOIN song AS s "
				+ "USING(song_id) "+sqlWithParams.getSql();
		Object[] params=sqlWithParams.getParams();
		Integer theResult=template.queryForObject(sql, params, Integer.class);
		return theResult;
	}

	@Override
	public CallRecord getCallDao(CallRecord callRecord) {
		String sql="SELECT cr.call_id,s.song_name,s.song_owner,cr.call_source,song_sell_time,cr.call_version,s.song_last_modify_time,s.song_cover "
				+ "FROM call_record AS cr "
				+ "LEFT JOIN song AS s "
				+ "USING(song_id) "
				+ "WHERE cr.call_id = ?";
		CallRecord theResult=template.queryForObject(sql, new Object[]{callRecord.getCallId()}, new RowMapper<CallRecord>(){

			@Override
			public CallRecord mapRow(ResultSet rs, int row)
					throws SQLException {
				CallRecord callRecord=new CallRecord();
				callRecord.setCallId(rs.getString("call_id"));
				callRecord.setCallSource(rs.getString("call_source"));
				callRecord.setCallVersion(rs.getShort("call_version"));
				Song song=new Song();
				song.setSongName(rs.getString("song_name"));
				song.setSongOwner(rs.getString("song_owner"));
				song.setSongSellTime(Util.date2String(rs.getDate("song_sell_time")));
				song.setSongLastModifyTime(Util.date2String(rs.getDate("song_last_modify_time")));
				song.setSongCover(rs.getString("song_cover"));
				callRecord.setSong(song);
				return callRecord;
			}
			
		});
		return theResult;
	}

	@Override
	public DaoResult saveCallDao(CallRecord callRecord) {
		String sql="INSERT INTO call_record(call_id,song_id,call_source,call_version) "
				+ "VALUES(?,?,?,?)";
		int result=template.update(sql, Util.getCharId("CR-", 10),
							callRecord.getSong().getSongId(),
							callRecord.getCallSource(),
							callRecord.getCallVersion());
		if(result==0){
			logger.log(Level.WARNING, "插入新的call表记录失败");
			return new DaoResult(false, "插入失败");
		}
		else{
			logger.log(Level.INFO, "插入新的call表记录成功");
			return new DaoResult(true, null);
		}
	}

	@Override
	public DaoResult deleteCallDao(CallRecord callRecord) {
		try{
			String sql="DELETE FROM call_record "
					+ "WHERE call_id = ?";
			template.update(sql, callRecord.getCallId());
			logger.log(Level.INFO, "删除id为"+callRecord.getCallId()+"的call表记录成功");
			return new DaoResult(true, null);
		}catch(Exception ex){
			logger.log(Level.WARNING, "删除id为"+callRecord.getCallId()+"的call表记录失败");
			ex.printStackTrace();
			return new DaoResult(false, "删除失败");
		}
	}

	@Override
	public DaoResult deleteCallDao(List<CallRecord> callRecords) {
		try{
			String sql="DELETE FROM call_record "
					+ "WHERE call_id = ?";
			List<Object[]> batchArgs=new ArrayList<Object[]>();
			for(int i=0;i<callRecords.size();i++){
				Object[] objectArray=new Object[1];
				objectArray[0]=callRecords.get(i).getCallId();
				batchArgs.add(objectArray);
			}
			template.batchUpdate(sql, batchArgs);
			logger.log(Level.INFO, "批删除call表记录成功");
			return new DaoResult(true, null);
		}catch(Exception ex){
			logger.log(Level.WARNING, "批删除call表记录失败");
			ex.printStackTrace();
			return new DaoResult(false, "批删除失败");
		}
	}
	
}
