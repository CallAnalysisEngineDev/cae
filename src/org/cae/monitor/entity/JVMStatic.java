package org.cae.monitor.entity;

import org.cae.entity.Entity;
import org.cae.entity.IBuilder;

public class JVMStatic extends Entity {

	private String jvmName;
	private String jvmVersion;
	private String jvmVendor;
	private String jvmStartTime;
	private String jvmInputArgs;
	private Integer jvmAvailableProcessors;
	private Integer jvmMemoryTotal;

	private JVMStatic(Builder builder) {
		this.jvmName = builder.jvmName;
		this.jvmVersion = builder.jvmVersion;
		this.jvmVendor = builder.jvmVendor;
		this.jvmStartTime = builder.jvmStartTime;
		this.jvmInputArgs = builder.jvmInputArgs;
		this.jvmAvailableProcessors = builder.jvmAvailableProcessors;
		this.jvmMemoryTotal = builder.jvmMemoryTotal;
	}

	public static class Builder implements IBuilder<JVMStatic> {

		private String jvmName;
		private String jvmVersion;
		private String jvmVendor;
		private String jvmStartTime;
		private String jvmInputArgs;
		private Integer jvmAvailableProcessors;
		private Integer jvmMemoryTotal;

		public Builder() {
		}

		public Builder jvmName(String jvmName) {
			this.jvmName = jvmName;
			return this;
		}

		public Builder jvmVersion(String jvmVersion) {
			this.jvmVersion = jvmVersion;
			return this;
		}

		public Builder jvmVendor(String jvmVendor) {
			this.jvmVendor = jvmVendor;
			return this;
		}

		public Builder jvmStartTime(String jvmStartTime) {
			this.jvmStartTime = jvmStartTime;
			return this;
		}

		public Builder jvmInputArgs(String jvmInputArgs) {
			this.jvmInputArgs = jvmInputArgs;
			return this;
		}

		public Builder jvmAvailableProcessors(Integer jvmAvailableProcessors) {
			this.jvmAvailableProcessors = jvmAvailableProcessors;
			return this;
		}

		public Builder jvmMemoryTotal(Integer jvmMemoryTotal) {
			this.jvmMemoryTotal = jvmMemoryTotal;
			return this;
		}

		@Override
		public JVMStatic build() {
			return new JVMStatic(this);
		}

	}

	public String getJvmName() {
		return jvmName;
	}

	public String getJvmVersion() {
		return jvmVersion;
	}

	public String getJvmVendor() {
		return jvmVendor;
	}

	public String getJvmStartTime() {
		return jvmStartTime;
	}

	public String getJvmInputArgs() {
		return jvmInputArgs;
	}

	public Integer getJvmAvailableProcessors() {
		return jvmAvailableProcessors;
	}

	public Integer getJvmMemoryTotal() {
		return jvmMemoryTotal;
	}
}
