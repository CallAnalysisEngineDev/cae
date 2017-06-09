package org.cae.service;

import java.util.List;

import org.cae.common.ServiceResult;
import org.cae.monitor.entity.CpuInfo;
import org.cae.monitor.entity.JVMClassLoad;
import org.cae.monitor.entity.JVMMemory;
import org.cae.monitor.entity.JVMThread;
import org.cae.monitor.entity.MemoryInfo;
import org.cae.monitor.entity.ProcessInfo;

public interface IMonitorService {

	ServiceResult queryStaticInfoService();
	
	CpuInfo queryCpuService();
	
	MemoryInfo queryMemoryService();
	
	List<ProcessInfo> queryProcessService();
	
	JVMMemory queryJvmMemoryService();
	
	JVMThread queryJvmThreadService();
	
	JVMClassLoad queryJvmClassService();
}
