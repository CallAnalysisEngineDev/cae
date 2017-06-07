package org.cae.service.impl;

import org.cae.common.ServiceResult;
import org.cae.monitor.entity.CpuInfo;
import org.cae.monitor.entity.JVMClassLoad;
import org.cae.monitor.entity.JVMMemory;
import org.cae.monitor.entity.JVMThread;
import org.cae.monitor.entity.MemoryInfo;
import org.cae.monitor.entity.ProcessInfo;
import org.cae.service.IMonitorService;
import org.springframework.stereotype.Service;

@Service("monitorService")
public class MonitorServiceImpl implements IMonitorService {

	@Override
	public ServiceResult queryStaticInfoService() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public CpuInfo queryCpuService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemoryInfo queryMemoryService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProcessInfo queryProcessService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JVMMemory queryJvmMemoryService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JVMThread queryJvmThreadService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JVMClassLoad queryJvmClassService() {
		// TODO Auto-generated method stub
		return null;
	}

}
