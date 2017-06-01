package org.cae.controller;

import java.util.Map;

import org.cae.monitor.entity.CpuInfo;
import org.cae.monitor.entity.JVMClassLoad;
import org.cae.monitor.entity.JVMMemory;
import org.cae.monitor.entity.JVMThread;
import org.cae.monitor.entity.MemoryInfo;
import org.cae.monitor.entity.ProcessInfo;

public interface IMonitorController {

	Map<String,Object> queryForHomepageController();
	
	boolean heartbeatController();
	
	CpuInfo queryCpuController();
	
	MemoryInfo queryMemoryController();
	
	ProcessInfo queryProcessController();
	
	JVMMemory queryJvmMemoryController();
	
	JVMThread queryJvmThreadController();
	
	JVMClassLoad queryJvmClassController();
}
