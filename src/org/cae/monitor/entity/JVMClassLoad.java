package org.cae.monitor.entity;

import top.starrysea.kql.entity.Entity;

public class JVMClassLoad implements Entity {

	private String time;
	private Integer jvmClassLoadedCount;

	public JVMClassLoad(String time, Integer jvmClassLoadedCount) {
		this.time = time;
		this.jvmClassLoadedCount = jvmClassLoadedCount;
	}

	public String getTime() {
		return time;
	}

	public Integer getJvmClassLoadedCount() {
		return jvmClassLoadedCount;
	}
}
