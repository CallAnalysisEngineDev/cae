package org.cae.monitor.entity;

import java.util.ArrayList;
import java.util.List;

import org.cae.common.Generator;
import org.cae.entity.Entity;
import org.cae.entity.IBuilder;

public class CpuInfo extends Entity{

	private String time;
	private List<Cpu> cpus;
	private double cpuTotalUseRate;
	
	public static class Builder implements IBuilder<CpuInfo>{

		private String time;
		private List<Cpu> cpus;
		private double cpuTotalUseRate;
		public Builder(){
			cpus=Generator.arrayList();
		}
		public Builder time(String time){
			this.time=time;
			return this;
		}
		public Builder cpu(Cpu cpu){
			this.cpus.add(cpu);
			return this;
		}
		public Builder cpuTotalUseRate(double cpuTotalUseRate){
			this.cpuTotalUseRate=cpuTotalUseRate;
			return this;
		}
		@Override
		public CpuInfo build() {
			return new CpuInfo(this);
		}
	}
	
	class Cpu{
		private String cpuType;
		private double useRate;
		public Cpu(String cpuType,double useRate){
			this.cpuType=cpuType;
			this.useRate=useRate;
		}
		public String getCpuType() {
			return cpuType;
		}
		public double getUseRate() {
			return useRate;
		}
	}

	private CpuInfo(Builder builder){
		this.time=builder.time;
		this.cpus=builder.cpus;
		this.cpuTotalUseRate=builder.cpuTotalUseRate;
	}
	
	public String getTime() {
		return time;
	}

	public List<Cpu> getCpus() {
		return cpus;
	}

	public double getCpuTotalUseRate() {
		return cpuTotalUseRate;
	}
	
}
