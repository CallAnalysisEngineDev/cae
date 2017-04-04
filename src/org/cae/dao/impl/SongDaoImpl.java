package org.cae.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import org.cae.common.Condition;
import org.cae.common.DaoResult;
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
	public List<Song> getAllSongDao(Condition condition, Song song) {
		SqlWithParams sqlWithParams=getTheSqlForGetAll(song);
		String sql="SELECT song_id,song_cover,song_owner,song_name "
				+ "FROM song "
				+sqlWithParams.getSql()
				+"ORDER BY song_last_modify_time DESC "
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
		String sql="SELECT COUNT(*) "
				+ "FROM song "
				+sqlWithParams.getSql();
		Object[] params=sqlWithParams.getParams();
		Integer theResult=template.queryForObject(sql, params, Integer.class);
		return theResult;
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

}
