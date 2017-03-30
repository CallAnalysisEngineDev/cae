package org.cae.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.cae.common.Condition;
import org.cae.common.ServiceResult;
import org.cae.dao.ICallDao;
import org.cae.entity.CallRecord;
import org.cae.service.ICallService;
import org.springframework.stereotype.Service;

@Service("callService")
public class CallServiceImpl implements ICallService {
	
	@Resource(name="callDao")
	private ICallDao callDao;
	@Resource(name="callLucene")
	private ICallDao callLucene;
	
	@Override
	public ServiceResult queryAllCallService(Condition condition,
			CallRecord callRecord) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ServiceResult queryCallService(CallRecord callRecord) {
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
