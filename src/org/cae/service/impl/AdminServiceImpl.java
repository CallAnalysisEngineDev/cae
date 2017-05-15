package org.cae.service.impl;

import org.cae.common.ServiceResult;
import org.cae.dao.IAdminDao;
import org.cae.entity.Admin;
import org.cae.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("adminService")
public class AdminServiceImpl implements IAdminService {

	@Autowired
	private IAdminDao adminDao;
	
	@Override
	public ServiceResult loginService(Admin admin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void generateKey() {
		// TODO Auto-generated method stub

	}

}
