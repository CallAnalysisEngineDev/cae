package org.cae.monitor.entity;

import org.cae.entity.Entity;

public class JVMStatic extends Entity{

	private String jvmName;
	private String jvmVersion;
	private String jvmVendor;
	private String jvmStartTime;
	private String jvmInputArgs;
	private Integer jvmAvailableProcessors;
	private Integer jvmMemoryTotal;
	public String getJvmName() {
		return jvmName;
	}
	public void setJvmName(String jvmName) {
		this.jvmName = jvmName;
	}
	public String getJvmVersion() {
		return jvmVersion;
	}
	public void setJvmVersion(String jvmVersion) {
		this.jvmVersion = jvmVersion;
	}
	public String getJvmVendor() {
		return jvmVendor;
	}
	public void setJvmVendor(String jvmVendor) {
		this.jvmVendor = jvmVendor;
	}
	public String getJvmStartTime() {
		return jvmStartTime;
	}
	public void setJvmStartTime(String jvmStartTime) {
		this.jvmStartTime = jvmStartTime;
	}
	public String getJvmInputArgs() {
		return jvmInputArgs;
	}
	public void setJvmInputArgs(String jvmInputArgs) {
		this.jvmInputArgs = jvmInputArgs;
	}
	public Integer getJvmAvailableProcessors() {
		return jvmAvailableProcessors;
	}
	public void setJvmAvailableProcessors(Integer jvmAvailableProcessors) {
		this.jvmAvailableProcessors = jvmAvailableProcessors;
	}
	public Integer getJvmMemoryTotal() {
		return jvmMemoryTotal;
	}
	public void setJvmMemoryTotal(Integer jvmMemoryTotal) {
		this.jvmMemoryTotal = jvmMemoryTotal;
	}
}
