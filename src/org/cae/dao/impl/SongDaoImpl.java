package org.cae.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.cae.common.Condition;
import org.cae.common.DaoResult;
import org.cae.common.IConstant;
import org.cae.common.SqlWithParams;
import org.cae.common.Util;
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
	public Map<String, Object> getSongForHomepageDao() {
		Map<String,Object> theResult=new HashMap<String,Object>();
		logger.info("开始查询热点歌曲名单,当前的限制条数HOMEPAGE_RED_LIMIT="+IConstant.HOMEPAGE_RED_LIMIT);
		String sql="SELECT song_id,song_name,song_cover,(song_click/timestampdiff(hour,song_create_time,'"+Util.getNowTime()+"')) AS clickrate "
				+ "FROM song "
				+ "ORDER BY clickrate DESC "
				+ "LIMIT "+IConstant.HOMEPAGE_RED_LIMIT;
		RowMapper<Map<String,Object>> rowMapper=new RowMapper<Map<String,Object>>() {
			
			@Override
			public Map<String, Object> mapRow(ResultSet rs, int row)
					throws SQLException {
				Map<String, Object> map=new HashMap<String,Object>();
				map.put("songId", rs.getString("song_id"));
				map.put("songName", rs.getString("song_name"));
				map.put("songCover", rs.getString("song_cover"));
				return map;
			}
			
		};
		List<Map<String,Object>> redList=template.query(sql, rowMapper);
		logger.info("开始查询最新修改歌曲名单,当前限制条数HOMEPAGE_NEWEST_LIMIT="+IConstant.HOMEPAGE_NEWEST_LIMIT);
		sql="SELECT song_id,song_name,song_cover "
			+ "FROM song "
			+ "ORDER BY song_last_modify_time DESC,song_name ASC "
			+ "LIMIT "+IConstant.HOMEPAGE_NEWEST_LIMIT;
		List<Map<String,Object>> newestList=template.query(sql, rowMapper);
		logger.info("查询完毕,热点歌曲数有"+redList.size()+"条,最新修改歌曲数有"+newestList.size()+"条");
		theResult.put("red", redList);
		theResult.put("newest", newestList);
		return theResult;
	}
	
	@Override
	public List<Song> getAllSongDao(Condition condition, Song song) {
		SqlWithParams sqlWithParams=getTheSqlForGetAll(song);
		//获取所有的歌曲
		String sql="SELECT song_id,song_cover,song_owner,song_name "
				+ "FROM song "
				+ sqlWithParams.getSql()
				+"ORDER BY song_last_modify_time DESC,song_name ASC "
				+ "LIMIT "+condition.getPageStart()+","+condition.getPageLimit();
		Object[] params=sqlWithParams.getParams();
		List<Song> theResult=template.query(sql, params, new RowMapper<Song>(){

			@Override
			public Song mapRow(ResultSet rs, int row) throws SQLException {
				Song song=new Song();
				song.setSongId(rs.getString("song_id"));
				song.setSongCover(rs.getString("song_cover"));
				song.setSongOwner(rs.getString("song_owner"));
				song.setSongName(rs.getString("song_name"));
				return song;
			}
			
		});
		return theResult;
	}
	
	//根据搜索条件解析成占位符的sql以及参数列表
	private SqlWithParams getTheSqlForGetAll(Song song){
		StringBuffer buffer=new StringBuffer();
		int insertIndex;
		Object[] preParams=new Object[1];
		int paramsIndex=0;
		buffer.append("WHERE 1=1 ");
		
		if(Util.isNull(song.getSongName())){
			insertIndex=buffer.indexOf("WHERE")+5;
			buffer.insert(insertIndex, " song_name LIKE ? AND ");
			preParams[paramsIndex]="%"+song.getSongName()+"%";
			paramsIndex++;
		}
		
		Object[] params=new Object[paramsIndex];
		System.arraycopy(preParams, 0, params, 0, paramsIndex);
		return new SqlWithParams(buffer.toString(),params);
	}
	
	@Override
	public Integer getSongCountDao(Condition condition, Song song) {
		SqlWithParams sqlWithParams=getTheSqlForGetAll(song);
		//获取一定条件下的数据条数,通常用于getAll的分页计算
		String sql="SELECT COUNT(*) "
				+ "FROM song "
				+ sqlWithParams.getSql();
		Object[] params=sqlWithParams.getParams();
		Integer theResult=template.queryForObject(sql, params, Integer.class);
		return theResult;
	}
	
	@Override
	public Song getSongDao(Song song) {
		try{
			String sql="SELECT song_name,song_owner,song_sell_time,song_last_modify_time,song_cover,song_id,song_video "
					+ "FROM song "
					+ "WHERE song_id = ?";
			Song theResult=template.queryForObject(sql, new Object[]{song.getSongId()}, new RowMapper<Song>() {

				@Override
				public Song mapRow(ResultSet rs, int row)
						throws SQLException {
					Song song=new Song();
					song.setSongName(rs.getString("song_name"));
					song.setSongOwner(rs.getString("song_owner"));
					song.setSongSellTime(Util.date2String(rs.getDate("song_sell_time")));
					song.setSongLastModifyTime(Util.date2String(rs.getDate("song_last_modify_time")));
					song.setSongCover(rs.getString("song_cover"));
					song.setSongId(rs.getString("song_id"));
					song.setSongVideo(rs.getShort("song_video"));
					return song;
				}
				
			});
			return theResult;
		}catch(Exception ex){
			logger.error(ex.getMessage());
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public DaoResult saveSongDao(Song song) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DaoResult deleteSongDao(Song song) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Logger getLogger() {
		return logger;
	}

}
