package org.cae.dao.impl;

import java.util.List;
import java.util.logging.Logger;

import org.cae.common.DaoResult;
import org.cae.dao.ICallDao;
import org.cae.entity.CallRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("callDao")
public class CallDaoImpl implements ICallDao {

	private Logger logger=Logger.getLogger(this.getClass().getName());
	
	@Autowired
	private JdbcTemplate template;

	@Override
	public List<CallRecord> getAllCallDao(CallRecord callRecord) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CallRecord getCallDao(CallRecord callRecord) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DaoResult saveCallDao(CallRecord callRecord) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DaoResult deleteCallDao(CallRecord callRecord) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DaoResult deleteCallDao(List<CallRecord> callRecords) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DaoResult updateCallDao(CallRecord callRecord) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
