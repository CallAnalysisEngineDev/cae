package org.cae.monitor.entity;

import org.cae.entity.Entity;

public class JVMClassLoad extends Entity {

	private String time;
	private Integer jvmClassLoadedCount;
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Integer getJvmClassLoadedCount() {
		return jvmClassLoadedCount;
	}
	public void setJvmClassLoadedCount(Integer jvmClassLoadedCount) {
		this.jvmClassLoadedCount = jvmClassLoadedCount;
	}
	
}
