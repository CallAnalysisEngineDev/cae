package org.cae.monitor.entity;

import java.util.List;

import org.cae.entity.Entity;

public class CpuInfo extends Entity {

	private String time;
	private List<Cpu> cpus;
	private double cpuTotalUseRate;
	
	class Cpu{
		private String cpuType;
		private double useRate;
		public String getCpuType() {
			return cpuType;
		}
		public void setCpuType(String cpuType) {
			this.cpuType = cpuType;
		}
		public double getUseRate() {
			return useRate;
		}
		public void setUseRate(double useRate) {
			this.useRate = useRate;
		}
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public List<Cpu> getCpus() {
		return cpus;
	}

	public void setCpus(List<Cpu> cpus) {
		this.cpus = cpus;
	}

	public double getCpuTotalUseRate() {
		return cpuTotalUseRate;
	}

	public void setCpuTotalUseRate(double cpuTotalUseRate) {
		this.cpuTotalUseRate = cpuTotalUseRate;
	}
	
}
