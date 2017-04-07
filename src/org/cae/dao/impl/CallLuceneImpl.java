package org.cae.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.cae.common.Condition;
import org.cae.common.DaoResult;
import org.cae.dao.ICallDao;
import org.cae.entity.CallRecord;
import org.springframework.stereotype.Repository;

@Repository("callLucene")
public class CallLuceneImpl implements ICallDao {

	@Resource(name="callLuceneAdaptee")
	private CallLuceneAdaptee callLucene;
	
	@Override
	public List<CallRecord> getAllCallDao(Condition condition, CallRecord callRecord) {
		return callLucene.getAllCallLucene(callRecord);
	}

	@Override
	public CallRecord getCallDao(CallRecord callRecord) {
		return null;
	}

	@Override
	public DaoResult saveCallDao(CallRecord callRecord) {
		return callLucene.saveCallLucene(callRecord);
	}

	@Override
	public DaoResult deleteCallDao(CallRecord callRecord) {
		return callLucene.deleteCallLucene(callRecord);
	}

	@Override
	public DaoResult deleteCallDao(List<CallRecord> callRecords) {
		DaoResult daoResult=new DaoResult(true, null);
		String errInfo = null;
		for(CallRecord callRecord:callRecords){
			DaoResult result=callLucene.deleteCallLucene(callRecord);
			if(!result.isResult()){
				daoResult.setResult(false);
				errInfo+=callRecord.getCallId()+",";
			}
		}
		if(!daoResult.isResult())
			daoResult.setErrInfo(errInfo.substring(0, errInfo.length()-2)+" has failed");
		return daoResult;
	}

	@Override
	public Integer getCallCountDao(Condition condition, CallRecord callRecord) {
		return null;
	}

	@Override
	public Logger getLogger() {
		return callLucene.getLogger();
	}

}
