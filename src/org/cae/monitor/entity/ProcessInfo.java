package org.cae.monitor.entity;

import org.cae.entity.Entity;

public class ProcessInfo extends Entity {

	private String time;
	private String processName;
	private String processId;
	private String processStartTime;
	private double processMemoryRate;
	private double processCpuRate;
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getProcessName() {
		return processName;
	}
	public void setProcessName(String processName) {
		this.processName = processName;
	}
	public String getProcessId() {
		return processId;
	}
	public void setProcessId(String processId) {
		this.processId = processId;
	}
	public String getProcessStartTime() {
		return processStartTime;
	}
	public void setProcessStartTime(String processStartTime) {
		this.processStartTime = processStartTime;
	}
	public double getProcessMemoryRate() {
		return processMemoryRate;
	}
	public void setProcessMemoryRate(double processMemoryRate) {
		this.processMemoryRate = processMemoryRate;
	}
	public double getProcessCpuRate() {
		return processCpuRate;
	}
	public void setProcessCpuRate(double processCpuRate) {
		this.processCpuRate = processCpuRate;
	}
}
