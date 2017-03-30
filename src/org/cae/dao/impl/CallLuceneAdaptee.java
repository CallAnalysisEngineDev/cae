package org.cae.dao.impl;

import java.util.List;
import java.util.logging.Logger;

import org.cae.common.DaoResult;
import org.cae.entity.CallRecord;
import org.springframework.stereotype.Repository;

@Repository("callLuceneAdaptee")
public class CallLuceneAdaptee {

	private Logger logger=Logger.getLogger(this.getClass().getName());
	
	public List<CallRecord> getAllCallLucene(CallRecord callRecord) {
		// TODO Auto-generated method stub
		return null;
	}

	public DaoResult saveCallLucene(CallRecord callRecord) {
		// TODO Auto-generated method stub
		return null;
	}

	public DaoResult deleteCallLucene(CallRecord callRecord) {
		// TODO Auto-generated method stub
		return null;
	}

	public DaoResult updateCallLucene(CallRecord callRecord) {
		// TODO Auto-generated method stub
		return null;
	}

}
