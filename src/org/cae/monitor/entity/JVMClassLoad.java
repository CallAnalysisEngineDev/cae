package org.cae.monitor.entity;

import org.cae.entity.Entity;

public class JVMClassLoad extends Entity {

	private String time;
	private Integer jvmClassLoadedCount;
	public JVMClassLoad(String time,Integer jvmClassLoadedCount){
		this.time=time;
		this.jvmClassLoadedCount=jvmClassLoadedCount;
	}
	public String getTime() {
		return time;
	}
	public Integer getJvmClassLoadedCount() {
		return jvmClassLoadedCount;
	}
}
