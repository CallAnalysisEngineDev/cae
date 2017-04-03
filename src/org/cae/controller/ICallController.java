package org.cae.controller;

import java.util.List;
import java.util.Map;

import org.cae.common.Condition;
import org.cae.entity.CallRecord;

public interface ICallController {

	public Map<String,Object> queryCallForHomepage();
	
	public Map<String,Object> queryAllCallController(Condition condition,CallRecord callRecord);
	
	public Map<String,Object> queryCallController(CallRecord callRecord);
	
	public Map<String,Object> addCallController(CallRecord callRecord);
	
	public Map<String,Object> removeCallController(CallRecord callRecord);
	
	public Map<String,Object> removeCallController(List<CallRecord> callRecords);
	
}
