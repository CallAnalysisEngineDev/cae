package org.cae.controller;

import java.util.List;
import java.util.Map;

import org.cae.common.Condition;
import org.cae.vo.CallRecord;
import org.cae.vo.Song;

public interface ICallController {

	Map<String, Object> querySongForHomepageController();

	Map<String, Object> queryAllSongController(Condition condition, Song song);

	Map<String, Object> queryCallController(CallRecord callRecord);

	Map<String, Object> addCallController(CallRecord callRecord);

	Map<String, Object> removeCallController(CallRecord callRecord);

	Map<String, Object> removeCallController(List<CallRecord> callRecords);

	Map<String, Object> addSongController(Song song);

	Map<String, Object> removeSongController(Song song);
}
