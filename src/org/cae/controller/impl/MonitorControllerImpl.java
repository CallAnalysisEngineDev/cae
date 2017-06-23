package org.cae.controller.impl;

import java.util.Map;

import org.cae.common.Generator;
import org.cae.common.ServiceResult;

import static org.cae.common.Util.toJson;

import org.cae.controller.IMonitorController;
import org.cae.service.IMonitorService;
import org.springframework.beans.factory.annotation.Autowired;

public class MonitorControllerImpl implements IMonitorController {

	@Autowired
	private IMonitorService monitorService;
	
	@Override
	public String queryForHomepageController() {
		Map<String,Object> theResult=Generator.hashMap();
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
		return toJson(theResult);
	}

	@Override
	public boolean heartbeatController() {
		return true;
	}

	@Override
	public String queryCpuController() {
		return toJson(monitorService.queryCpuService());
	}

	@Override
	public String queryMemoryController() {
		return toJson(monitorService.queryMemoryService());
	}

	@Override
	public String queryProcessController() {
		return toJson(monitorService.queryProcessService());
	}

	@Override
	public String queryJvmMemoryController() {
		return toJson(monitorService.queryJvmMemoryService());
	}

	@Override
	public String queryJvmThreadController() {
		return toJson(monitorService.queryJvmThreadService());
	}

	@Override
	public String queryJvmClassController() {
		return toJson(monitorService.queryJvmClassService());
	}

}
