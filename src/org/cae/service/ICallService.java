package org.cae.service;

import java.util.List;
import java.util.Map;

import org.cae.common.Condition;
import org.cae.common.ServiceResult;
import org.cae.entity.CallRecord;

public interface ICallService {

	public Map<String,Object> queryAllCallService(Condition condition,CallRecord callRecord);
	
	public Map<String,Object> queryCallService(CallRecord callRecord);
	
	public ServiceResult addCallService(CallRecord callRecord);
	
	public ServiceResult removeCallService(CallRecord callRecord);
	
	public ServiceResult removeCallService(List<CallRecord> callRecords);
	
	public ServiceResult modifyCallService(CallRecord callRecord);
}
