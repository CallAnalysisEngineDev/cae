package org.cae.controller.impl;

import java.util.HashMap;
import java.util.Map;

import org.cae.common.ServiceResult;
import org.cae.common.Util;
import org.cae.controller.IMonitorController;
import org.cae.service.IMonitorService;
import org.springframework.beans.factory.annotation.Autowired;

public class MonitorControllerImpl implements IMonitorController {

	@Autowired
	private IMonitorService monitorService;
	
	@Override
	public String queryForHomepageController() {
		Map<String,Object> theResult=new HashMap<String,Object>();
		ServiceResult serviceResult=monitorService.queryStaticInfoService();
		if(serviceResult.isSuccessed()){
			theResult=(Map<String, Object>) serviceResult.getResult();
			theResult.put("cpu", monitorService.queryCpuService());
			theResult.put("memory", monitorService.queryMemoryService());
			theResult.put("process", monitorService.queryProcessService());
			theResult.put("jvmMemory", monitorService.queryJvmMemoryService());
			theResult.put("jvmThread", monitorService.queryJvmThreadService());
			theResult.put("jvmClassLoad", monitorService.queryJvmClassService());
		}
		return Util.toJson(theResult);
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
		return Util.toJson(monitorService.queryMemoryService());
	}

	@Override
	public String queryProcessController() {
		return Util.toJson(monitorService.queryProcessService());
	}

	@Override
	public String queryJvmMemoryController() {
		return Util.toJson(monitorService.queryJvmMemoryService());
	}

	@Override
	public String queryJvmThreadController() {
		return Util.toJson(monitorService.queryJvmThreadService());
	}

	@Override
	public String queryJvmClassController() {
		return Util.toJson(monitorService.queryJvmClassService());
	}

}
