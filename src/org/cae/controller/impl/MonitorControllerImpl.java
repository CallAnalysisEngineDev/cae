package org.cae.controller.impl;

import org.cae.common.Util;
import org.cae.controller.IMonitorController;
import org.cae.service.IMonitorService;
import org.springframework.beans.factory.annotation.Autowired;

public class MonitorControllerImpl implements IMonitorController {

	@Autowired
	private IMonitorService monitorService;
	
	@Override
	public String queryForHomepageController() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean heartbeatController() {
		return true;
	}

	@Override
	public String queryCpuController() {
		return Util.toJson(monitorService.queryCpuService());
	}

	@Override
	public String queryMemoryController() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String queryProcessController() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String queryJvmMemoryController() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String queryJvmThreadController() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String queryJvmClassController() {
		// TODO Auto-generated method stub
		return null;
	}

}
