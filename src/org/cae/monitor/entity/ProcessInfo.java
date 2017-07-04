package org.cae.monitor.entity;

import org.cae.entity.Entity;
import org.cae.entity.IBuilder;

public class ProcessInfo extends Entity {

	private String processName;
	private String processId;
	private String processStartTime;
	private double processMemoryRate;
	private double processCpuRate;
	private ProcessInfo(Builder builder){
		this.processName=builder.processName;
		this.processId=builder.processId;
		this.processStartTime=builder.processStartTime;
		this.processMemoryRate=builder.processMemoryRate;
		this.processCpuRate=builder.processCpuRate;
	}
	
	public static class Builder implements IBuilder<ProcessInfo>{

		private String processName;
		private String processId;
		private String processStartTime;
		private double processMemoryRate;
		private double processCpuRate;
		public Builder(){}
		public Builder processName(String processName){
			this.processName=processName;
			return this;
		}
		public Builder processId(String processId){
			this.processId=processId;
			return this;
		}
		public Builder processStartTime(String processStartTime){
			this.processStartTime=processStartTime;
			return this;
		}
		public Builder processMemoryRate(double processMemoryRate){
			this.processMemoryRate=processMemoryRate;
			return this;
		}
		public Builder processCpuRate(double processCpuRate){
			this.processCpuRate=processCpuRate;
			return this;
		}
		@Override
		public ProcessInfo build() {
			return new ProcessInfo(this);
		}
		
	}
	public String getProcessName() {
		return processName;
	}
	public String getProcessId() {
		return processId;
	}
	public String getProcessStartTime() {
		return processStartTime;
	}
	public double getProcessMemoryRate() {
		return processMemoryRate;
	}
	public double getProcessCpuRate() {
		return processCpuRate;
	}
}
