package org.cae.dao.impl;

import org.apache.log4j.Logger;
import org.cae.common.DaoResult;
import org.cae.dao.IAdminDao;
import org.cae.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("adminDao")
public class AdminDaoImpl implements IAdminDao {

	private Logger logger=Logger.getLogger(this.getClass());
	@Autowired
	private JdbcTemplate template;
	
	@Override
	public Logger getLogger() {
		return logger;
	}

	@Override
	public DaoResult getAdminInfoDao(Admin admin) {
		// TODO Auto-generated method stub
		return null;
	}

}
