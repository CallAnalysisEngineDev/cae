package org.cae.monitor.entity;

import org.cae.entity.Entity;

public class JVMThread extends Entity {

	private String time;
	private Integer jvmDamonThreadCount;
	private Integer jvmActiveThreadCount;
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Integer getJvmDamonThreadCount() {
		return jvmDamonThreadCount;
	}
	public void setJvmDamonThreadCount(Integer jvmDamonThreadCount) {
		this.jvmDamonThreadCount = jvmDamonThreadCount;
	}
	public Integer getJvmActiveThreadCount() {
		return jvmActiveThreadCount;
	}
	public void setJvmActiveThreadCount(Integer jvmActiveThreadCount) {
		this.jvmActiveThreadCount = jvmActiveThreadCount;
	}
}
