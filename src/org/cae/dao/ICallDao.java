package org.cae.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.cae.common.Condition;
import org.cae.common.DaoResult;
import org.cae.entity.CallRecord;

public interface ICallDao {
	
	List<CallRecord> getAllCallDao(Condition condition, CallRecord callRecord);
	
	Integer getCallCountDao(Condition condition, CallRecord callRecord);
	
	CallRecord getCallDao(CallRecord callRecord);
	
	DaoResult saveCallDao(CallRecord callRecord);
	
	DaoResult deleteCallDao(CallRecord callRecord);
	
	DaoResult deleteCallDao(List<CallRecord> callRecords);
	
	Logger getLogger();
	
}
