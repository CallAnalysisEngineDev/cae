package org.cae.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.cae.common.Condition;
import org.cae.common.DaoResult;
import org.cae.common.Generator;
import org.cae.common.IConstant;
import org.cae.common.SqlWithParams;

import static org.cae.common.Util.*;
import static org.cae.common.IConstant.*;

import org.cae.dao.ISongDao;
import org.cae.entity.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("songDao")
public class SongDaoImpl implements ISongDao {

	private Logger logger=Logger.getLogger(this.getClass().getName());
	@Autowired
	private JdbcTemplate template;

	@Override
	public DaoResult getSongForHomepageDao() {
		Map<String,Object> theResult=Generator.hashMap();
		String sql="SELECT song_id,song_name,song_cover,(song_click/timestampdiff(hour,song_create_time,'"+getNowTime()+"')) AS clickrate "
				+ "FROM song "
				+ "ORDER BY clickrate DESC "
				+ "LIMIT "+HOMEPAGE_RED_LIMIT;
		RowMapper<Map<String,Object>> rowMapper=new RowMapper<Map<String,Object>>() {
			
			@Override
			public Map<String, Object> mapRow(ResultSet rs, int row)
					throws SQLException {
				Map<String, Object> map=Generator.hashMap();
				map.put("songId", rs.getString("song_id"));
				map.put("songName", rs.getString("song_name"));
				map.put("songCover", IConstant.STATIC_PREFIX+rs.getString("song_cover"));
				return map;
			}
			
		};
		List<Map<String,Object>> redList=template.query(sql, rowMapper);
		sql="SELECT song_id,song_name,song_cover "
			+ "FROM song "
			+ "ORDER BY song_last_modify_time DESC,song_name ASC "
			+ "LIMIT "+HOMEPAGE_NEWEST_LIMIT;
		List<Map<String,Object>> newestList=template.query(sql, rowMapper);
		theResult.put("red", redList);
		theResult.put("newest", newestList);
		return new DaoResult(true, theResult);
	}
	
	@Override
	public DaoResult getAllSongDao(Condition condition, Song song) {
		SqlWithParams sqlWithParams=getTheSqlForGetAll(condition,song);
		//获取所有的歌曲
		String sql="SELECT song_id,song_cover,song_owner,song_name "
				+ "FROM song "
				+ sqlWithParams.getWhere()
				+ sqlWithParams.getOrder()
				+ "LIMIT "+condition.getPageStart()+","+condition.getPageLimit();
		Object[] params=sqlWithParams.getParams();
		List<Song> theResult=template.query(sql, params, new RowMapper<Song>(){

			@Override
			public Song mapRow(ResultSet rs, int row) throws SQLException {
				return new Song.Builder()
							.songId(rs.getString("song_id"))
							.songCover(IConstant.STATIC_PREFIX+rs.getString("song_cover"))
							.songOwner(rs.getString("song_owner"))
							.songName(rs.getString("song_name"))
							.build();
			}
		});
		return new DaoResult(true, theResult);
	}
	
	//根据搜索条件解析成占位符的sql以及参数列表
	public SqlWithParams getTheSqlForGetAll(Condition condition, Song song){
		StringBuffer whereBuffer=new StringBuffer();
		StringBuffer orderBuffer=new StringBuffer();
		int insertIndex;
		Object[] preParams=new Object[1];
		int paramsIndex=0;
		whereBuffer.append("WHERE 1=1 ");
		
		if(isNotNull(song.getSongName())){//如果搜索的条件不是name
			insertIndex=whereBuffer.indexOf("WHERE")+5;
			whereBuffer.insert(insertIndex, " song_name LIKE ? AND ");//拼接where 
			preParams[paramsIndex]="%"+song.getSongName()+"%";
			paramsIndex++;
		}
		
		if(isNotNull(condition.getOrderBy())){
			String orderDir=condition.getOrderDir();
			orderBuffer.append("ORDER BY "+condition.getOrderBy()+" "+(orderDir!=null?orderDir:"DESC")+",song_name ASC ");
		}
		else{
			orderBuffer.append("ORDER BY song_sell_time DESC,song_name ASC ");
		}
		Object[] params=new Object[paramsIndex];//因为要传参数类型，所以用object[0]代替null
		System.arraycopy(preParams, 0, params, 0, paramsIndex);
		if(orderBuffer.length()>0)
			return new SqlWithParams(whereBuffer.toString(),orderBuffer.toString(),params);
		else
			return new SqlWithParams(whereBuffer.toString(),params);
	}
	
	@Override
	public DaoResult getSongCountDao(Condition condition, Song song) {
		SqlWithParams sqlWithParams=getTheSqlForGetAll(new Condition(),song);
		//获取一定条件下的数据条数,通常用于getAll的分页计算
		String sql="SELECT COUNT(*) "
				+ "FROM song "
				+ sqlWithParams.getWhere();
		Object[] params=sqlWithParams.getParams();
		Integer theResult=template.queryForObject(sql, params, Integer.class);
		return new DaoResult(true, theResult);
	}
	
	@Override
	public DaoResult getSongDao(Song song) {
		try{
			String sql="SELECT song_name,song_owner,song_sell_time,song_last_modify_time,song_cover,song_id,song_video "
					+ "FROM song "
					+ "WHERE song_id = ?";
			Song theResult=template.queryForObject(sql, new Object[]{song.getSongId()}, new RowMapper<Song>() {
				@Override
				public Song mapRow(ResultSet rs, int row)
						throws SQLException {
					return new Song.Builder()
								.songName(rs.getString("song_name"))
								.songOwner(rs.getString("song_owner"))
								.songSellTime(date2String(rs.getDate("song_sell_time")))
								.songLastModifyTime(date2String(rs.getDate("song_last_modify_time")))
								.songCover(IConstant.STATIC_PREFIX+rs.getString("song_cover"))
								.songId(rs.getString("song_id"))
								.songVideo(rs.getShort("song_video"))
								.build();
				}
			});
			return new DaoResult(true, theResult);
		}catch(Exception ex){
			logStackTrace(logger, ex.getStackTrace());
			ex.printStackTrace();
			return new DaoResult(false, ex.getMessage());
		}
	}

	@Override
	public DaoResult saveSongDao(Song song) {
		try{
			//添加一首歌曲的记录
			String sql = "INSERT INTO song(song_id,song_name,song_sell_time,song_owner,song_cover,song_create_time,song_click,song_last_modify_time,song_video)"
					+ "VALUES(?,?,?,?,?,?,?,?,?)";
			template.update(sql,song.getSongId(),
					song.getSongName(),
					song.getSongSellTime(),
					song.getSongOwner(),
					song.getSongCover(),
					getNowTime(),
					0,
					getNowDate(),
					song.getSongVideo());
			logger.info("插入新的歌曲记录成功");
			return new DaoResult(true, null);
		}catch(Exception ex){
			logStackTrace(logger, ex.getStackTrace());
			ex.printStackTrace();
			return new DaoResult(false, "删除失败");
		}
	}
	@Override
	public DaoResult deleteSongDao(Song song) {
		try{
			//删除一首歌曲的记录
			String sql="DELETE FROM song "
						+ "WHERE song_id = ?";
			template.update(sql,song.getSongId());
			logger.info("删除id为"+song.getSongId()+"的歌曲记录成功");
			return new DaoResult(true, null);
		}catch(Exception ex){
			logStackTrace(logger, ex.getStackTrace());
			ex.printStackTrace();
			return new DaoResult(false, "删除失败");
		}
	}
	@Override
	public Logger getLogger() {
		return logger;
	}

}
