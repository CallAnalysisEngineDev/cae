package org.cae.dao;

import java.util.List;

import org.cae.common.DaoResult;
import org.cae.entity.CallRecord;

public interface ICallLucene {

	public List<CallRecord> getAllCallLucene(CallRecord callRecord);
	
	public DaoResult saveCallLucene(CallRecord callRecord);
	
	public DaoResult deleteCallLucene(CallRecord callRecord);
	
	public DaoResult updateCallLucene(CallRecord callRecord);
}
