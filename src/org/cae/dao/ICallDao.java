package org.cae.dao;

import java.util.List;
import java.util.Map;

import org.cae.common.Condition;
import org.cae.common.DaoResult;
import org.cae.entity.CallRecord;

public interface ICallDao {

	public Map<String,Object> getCallForHomepageDao();
	
	public List<CallRecord> getAllCallDao(Condition condition, CallRecord callRecord);
	
	public CallRecord getCallDao(CallRecord callRecord);
	
	public DaoResult saveCallDao(CallRecord callRecord);
	
	public DaoResult deleteCallDao(CallRecord callRecord);
	
	public DaoResult deleteCallDao(List<CallRecord> callRecords);
	
	public DaoResult updateCallDao(CallRecord callRecord);
}
