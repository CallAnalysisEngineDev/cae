package org.cae.monitor.entity;

import top.starrysea.kql.entity.Entity;
import top.starrysea.kql.entity.IBuilder;

public class JVMThread implements Entity {

	private String time;
	private Integer jvmDamonThreadCount;
	private Integer jvmActiveThreadCount;

	private JVMThread(Builder builder) {
		this.time = builder.time;
		this.jvmDamonThreadCount = builder.jvmDamonThreadCount;
		this.jvmActiveThreadCount = builder.jvmActiveThreadCount;
	}

	public static class Builder implements IBuilder<JVMThread> {

		private String time;
		private Integer jvmDamonThreadCount;
		private Integer jvmActiveThreadCount;

		public Builder() {
		}

		public Builder time(String time) {
			this.time = time;
			return this;
		}

		public Builder jvmDamonThreadCount(Integer jvmDamonThreadCount) {
			this.jvmDamonThreadCount = jvmDamonThreadCount;
			return this;
		}

		public Builder jvmActiveThreadCount(Integer jvmActiveThreadCount) {
			this.jvmActiveThreadCount = jvmActiveThreadCount;
			return this;
		}

		@Override
		public JVMThread build() {
			return new JVMThread(this);
		}

	}

	public String getTime() {
		return time;
	}

	public Integer getJvmDamonThreadCount() {
		return jvmDamonThreadCount;
	}

	public Integer getJvmActiveThreadCount() {
		return jvmActiveThreadCount;
	}
}
