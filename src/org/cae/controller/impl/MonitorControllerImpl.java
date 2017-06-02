package org.cae.controller.impl;

import java.util.Map;

import org.cae.controller.IMonitorController;
import org.cae.monitor.entity.CpuInfo;
import org.cae.monitor.entity.JVMClassLoad;
import org.cae.monitor.entity.JVMMemory;
import org.cae.monitor.entity.JVMThread;
import org.cae.monitor.entity.MemoryInfo;
import org.cae.monitor.entity.ProcessInfo;
import org.cae.service.IMonitorService;
import org.springframework.beans.factory.annotation.Autowired;

public class MonitorControllerImpl implements IMonitorController {

	@Autowired
	private IMonitorService monitorService;
	
	@Override
	public Map<String, Object> queryForHomepageController() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean heartbeatController() {
		return true;
	}

	@Override
	public CpuInfo queryCpuController() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemoryInfo queryMemoryController() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProcessInfo queryProcessController() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JVMMemory queryJvmMemoryController() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JVMThread queryJvmThreadController() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JVMClassLoad queryJvmClassController() {
		// TODO Auto-generated method stub
		return null;
	}

}
