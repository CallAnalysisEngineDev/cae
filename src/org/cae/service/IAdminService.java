package org.cae.service;

import org.cae.common.ServiceResult;
import org.cae.entity.Admin;

public interface IAdminService {

	ServiceResult loginService(Admin admin);
	
	void generateKey();
}
