package org.cae.controller;

import java.util.List;
import java.util.Map;

import org.cae.common.Condition;
import org.cae.entity.CallRecord;
import org.cae.entity.Song;

public interface ICallController {

	public Map<String,Object> queryCallForHomepage();
	
	public Map<String,Object> queryAllSongController(Condition condition,Song song);
	
	public Map<String,Object> queryCallController(CallRecord callRecord);
	
	public Map<String,Object> addCallController(CallRecord callRecord);
	
	public Map<String,Object> removeCallController(CallRecord callRecord);
	
	public Map<String,Object> removeCallController(List<CallRecord> callRecords);
	
}
