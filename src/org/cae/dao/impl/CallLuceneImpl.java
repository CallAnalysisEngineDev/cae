package org.cae.dao.impl;

import java.util.List;

import org.cae.common.DaoResult;
import org.cae.dao.ICallLucene;
import org.cae.entity.CallRecord;
import org.springframework.stereotype.Repository;

@Repository("callLucene")
public class CallLuceneImpl implements ICallLucene {

	@Override
	public List<CallRecord> getAllCallLucene(CallRecord callRecord) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DaoResult saveCallLucene(CallRecord callRecord) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DaoResult deleteCallLucene(CallRecord callRecord) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DaoResult updateCallLucene(CallRecord callRecord) {
		// TODO Auto-generated method stub
		return null;
	}

}
