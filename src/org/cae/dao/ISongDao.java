package org.cae.dao;

import java.util.List;

import org.cae.common.Condition;
import org.cae.common.DaoResult;
import org.cae.entity.Song;

public interface ISongDao {

	public List<Song> getAllSongDao(Condition condition, Song song);
	
	public Integer getSongCountDao(Condition condition, Song song);
	
	public DaoResult saveSongDao(Song song);
	
	public DaoResult deleteSongDao(Song song);
}
