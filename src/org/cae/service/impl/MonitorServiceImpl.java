package org.cae.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.cae.common.Util;
import org.cae.monitor.entity.CpuInfo;
import org.cae.monitor.entity.CpuInfo.Cpu;
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
	public CpuInfo queryCpuService() {
		CpuInfo cpuInfo=new CpuInfo();
		cpuInfo.setTime(Util.getNowTime());
		cpuInfo.setCpuTotalUseRate(100);
		List<Cpu> cpus=new ArrayList<Cpu>();
		Cpu cpu=new Cpu();
		cpu.setCpuType("i7");
		cpu.setUseRate(100);
		cpus.add(cpu);
		cpuInfo.setCpus(cpus);
		return cpuInfo;
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
