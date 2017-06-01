package org.cae.monitor.entity;

import org.cae.entity.Entity;

public class MemoryInfo extends Entity {

	private String time;
	private double memoryUse;
	private double memoryFree;
	private double swapUse;
	private double swapFree;
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public double getMemoryUse() {
		return memoryUse;
	}
	public void setMemoryUse(double memoryUse) {
		this.memoryUse = memoryUse;
	}
	public double getMemoryFree() {
		return memoryFree;
	}
	public void setMemoryFree(double memoryFree) {
		this.memoryFree = memoryFree;
	}
	public double getSwapUse() {
		return swapUse;
	}
	public void setSwapUse(double swapUse) {
		this.swapUse = swapUse;
	}
	public double getSwapFree() {
		return swapFree;
	}
	public void setSwapFree(double swapFree) {
		this.swapFree = swapFree;
	}
	
}
