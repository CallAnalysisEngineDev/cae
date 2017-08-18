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
		Map<String, Object> theResult = Generator.hashMap();
		ServiceResult serviceResult = monitorService.queryStaticInfoService();
		if (serviceResult.isSuccessed()) {
			theResult = (Map<String, Object>) serviceResult.getResult();
			theResult.put("cpu", monitorService.queryCpuService());
			theResult.put("memory", monitorService.queryMemoryService());
			theResult.put("process", monitorService.queryProcessService());
			theResult.put("jvmMemory", monitorService.queryJvmMemoryService());
			theResult.put("jvmThread", monitorService.queryJvmThreadService());
			theResult
					.put("jvmClassLoad", monitorService.queryJvmClassService());
		}
		return toJson(theResult);
	}

	@Override
	public boolean heartbeatController() {
		return true;
	}

	@Override
	public String getMachineInfo() {
		Map<String, Object> theResult = Generator.hashMap();
		ServiceResult serviceResult = monitorService.queryStaticInfoService();
		if (serviceResult.isSuccessed()) {
			theResult = (Map<String, Object>) serviceResult.getResult();
			theResult.put("cpu", monitorService.queryCpuService());
			theResult.put("memory", monitorService.queryMemoryService());
			theResult.put("process", monitorService.queryProcessService());
			theResult.put("jvmMemory", monitorService.queryJvmMemoryService());
			theResult.put("jvmThread", monitorService.queryJvmThreadService());
			theResult
					.put("jvmClassLoad", monitorService.queryJvmClassService());
		}
		return toJson(theResult);
	}

	@Override
	public String gcController() {
		return toJson(monitorService.gcService());
	}

}
