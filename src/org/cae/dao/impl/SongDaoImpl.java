package org.cae.dao.impl;

import java.util.List;
import java.util.logging.Logger;

import org.cae.common.Condition;
import org.cae.common.DaoResult;
import org.cae.dao.ISongDao;
import org.cae.entity.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class SongDaoImpl implements ISongDao {

	private Logger logger=Logger.getLogger(this.getClass().getName());
	
	@Autowired
	private JdbcTemplate template;
	
	@Override
	public List<Song> getAllSongDao(Condition condition, Song song) {
		// TODO Auto-generated method stub
		return null;
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
