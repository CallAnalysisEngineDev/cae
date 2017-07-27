package org.cae.dao;

import org.cae.common.Condition;
import org.cae.common.DaoResult;
import org.cae.entity.Song;

public interface ISongDao extends IBaseDao {

	DaoResult getSongForHomepageDao();

	DaoResult getAllSongDao(Condition condition, Song song);

	DaoResult getSongCountDao(Condition condition, Song song);

	DaoResult getSongDao(Song song);

	DaoResult saveSongDao(Song song);

	DaoResult deleteSongDao(Song song);
}
