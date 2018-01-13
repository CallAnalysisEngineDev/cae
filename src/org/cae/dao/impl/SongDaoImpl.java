package org.cae.dao.impl;

import java.util.Map;

import org.apache.log4j.Logger;
import org.cae.common.Condition;
import org.cae.common.DaoResult;
import org.cae.common.Generator;
import org.cae.common.IConstant;

import static org.cae.common.Util.*;
import static org.cae.common.IConstant.*;

import org.cae.dao.ISongDao;
import org.cae.object.dto.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import top.starrysea.kql.clause.OrderByType;
import top.starrysea.kql.clause.SelectClause;
import top.starrysea.kql.clause.WhereType;
import top.starrysea.kql.facede.EntitySqlResult;
import top.starrysea.kql.facede.IntegerSqlResult;
import top.starrysea.kql.facede.KumaSqlDao;
import top.starrysea.kql.facede.ListSqlResult;

@Repository("songDao")
public class SongDaoImpl implements ISongDao {

	private Logger logger = Logger.getLogger(this.getClass().getName());
	@Autowired
	private KumaSqlDao kumaSqlDao;

	@Override
	public DaoResult getSongForHomepageDao() {
		Map<String, Object> theResult = Generator.hashMap();
		kumaSqlDao.selectMode();
		RowMapper<Map<String, Object>> rowMapper = (rs, row) -> {
			Map<String, Object> map = Generator.hashMap();
			map.put("songId", rs.getString("song_id"));
			map.put("songName", rs.getString("song_name"));
			map.put("songCover", IConstant.STATIC_PREFIX + rs.getString("song_cover"));
			return map;
		};
		ListSqlResult result = kumaSqlDao.select("song_id").select("song_name").select("song_cover")
				.select("(song_click/timestampdiff(hour,song_create_time,'" + getNowTime() + "')) as clickrate")
				.from(Song.class).orderBy("clickrate", OrderByType.DESC).limit(HOMEPAGE_RED_LIMIT)
				.endForList(rowMapper);
		theResult.put("red", result.getResult());

		result = kumaSqlDao.select("song_id").select("song_name").select("song_cover").from(Song.class)
				.orderBy("song_last_modify_time", OrderByType.DESC).orderBy("song_name", OrderByType.ASC)
				.limit(HOMEPAGE_NEWEST_LIMIT).endForList(rowMapper);
		theResult.put("newest", result.getResult());
		return new DaoResult(true, theResult);
	}

	@Override
	public DaoResult getAllSongDao(Condition condition, Song song) {
		kumaSqlDao.selectMode();
		KumaSqlDao temp = kumaSqlDao.select("song_id").select("song_cover").select("song_owner").select("song_name")
				.from(Song.class).where("song_name", WhereType.FUZZY, song.getSongName());
		ListSqlResult theResult;
		RowMapper<Song> rowMapper = (rs, row) -> new Song.Builder().songId(rs.getString("song_id"))
				.songCover(IConstant.STATIC_PREFIX + rs.getString("song_cover")).songOwner(rs.getString("song_owner"))
				.songName(rs.getString("song_name")).build();
		if (isNotNull(condition.getOrderBy())) {
			theResult = temp
					.orderBy(condition.getOrderBy(),
							condition.getOrderDir().equals("ASC") ? OrderByType.ASC : OrderByType.DESC)
					.orderBy("song_name", OrderByType.ASC).endForList(rowMapper);
		} else {
			theResult = temp.orderBy("song_sell_time", OrderByType.DESC).orderBy("song_name", OrderByType.ASC)
					.endForList(rowMapper);
		}
		return new DaoResult(true, theResult.getResult());
	}

	@Override
	public DaoResult getSongCountDao(Condition condition, Song song) {
		kumaSqlDao.selectMode();
		IntegerSqlResult theResult = kumaSqlDao.select(SelectClause.COUNT).from(Song.class)
				.where("song_name", WhereType.FUZZY, song.getSongName()).endForNumber();
		return new DaoResult(true, theResult.getResult());
	}

	@Override
	public DaoResult getSongDao(Song song) {
		EntitySqlResult<Song> theResult = kumaSqlDao.select("song_name").select("song_owner").select("song_sell_time")
				.select("song_last_modify_time").select("song_cover").select("song_id").select("song_video")
				.from(Song.class).where("song_id", WhereType.EQUALS, song.getSongId())
				.endForObject((rs, row) -> new Song.Builder().songName(rs.getString("song_name"))
						.songOwner(rs.getString("song_owner")).songSellTime(date2String(rs.getDate("song_sell_time")))
						.songLastModifyTime(date2String(rs.getDate("song_last_modify_time")))
						.songCover(IConstant.STATIC_PREFIX + rs.getString("song_cover")).songId(rs.getString("song_id"))
						.songVideo(rs.getShort("song_video")).build());
		return new DaoResult(true, theResult.getResult());
	}

	@Override
	public DaoResult saveSongDao(Song song) {
		kumaSqlDao.insertMode();
		kumaSqlDao.insert("song_id", song.getSongId()).insert("song_name", song.getSongName())
				.insert("song_sell_time", song.getSongSellTime()).insert("song_owner", song.getSongOwner())
				.insert("song_cover", song.getSongCover()).insert("song_create_time", song.getSongCreateTime())
				.insert("song_click", song.getSongClick()).insert("song_last_modify_time", song.getSongLastModifyTime())
				.insert("song_video", song.getSongVideo()).table(Song.class).end();
		return new DaoResult(true);
	}

	@Override
	public DaoResult deleteSongDao(Song song) {
		kumaSqlDao.deleteMode();
		kumaSqlDao.table(Song.class).where("song_id", WhereType.EQUALS, song.getSongId()).end();
		return new DaoResult(true);
	}

	@Override
	public Logger getLogger() {
		return logger;
	}

}
