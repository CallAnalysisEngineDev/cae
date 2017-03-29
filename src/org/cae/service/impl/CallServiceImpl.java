package org.cae.service.impl;

import java.util.List;
import java.util.Map;

import org.cae.common.Condition;
import org.cae.common.ServiceResult;
import org.cae.dao.ICallDao;
import org.cae.dao.ICallLucene;
import org.cae.entity.CallRecord;
import org.cae.service.ICallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("callService")
public class CallServiceImpl implements ICallService {

	@Autowired
	private ICallDao callDao;
	@Autowired
	private ICallLucene callLucene;
	
	@Override
	public Map<String, Object> queryAllCallService(Condition condition,
			CallRecord callRecord) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Map<String, Object> queryCallService(CallRecord callRecord) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ServiceResult addCallService(CallRecord callRecord) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ServiceResult removeCallService(CallRecord callRecord) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ServiceResult removeCallService(List<CallRecord> callRecords) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ServiceResult modifyCallService(CallRecord callRecord) {
		// TODO Auto-generated method stub
		return null;
	}
}
