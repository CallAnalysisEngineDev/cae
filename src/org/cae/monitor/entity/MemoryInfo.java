package org.cae.monitor.entity;

import org.cae.entity.Entity;
import org.cae.entity.IBuilder;

public class MemoryInfo extends Entity {

	private String time;
	private double memoryUse;
	private double memoryFree;
	private double swapUse;
	private double swapFree;

	private MemoryInfo(Builder builder) {
		this.time = builder.time;
		this.memoryUse = builder.memoryUse;
		this.memoryFree = builder.memoryFree;
		this.swapUse = builder.swapUse;
		this.swapFree = builder.swapFree;
	}

	public static class Builder implements IBuilder<MemoryInfo> {

		private String time;
		private double memoryUse;
		private double memoryFree;
		private double swapUse;
		private double swapFree;

		public Builder() {
		}

		public Builder time(String time) {
			this.time = time;
			return this;
		}

		public Builder memoryUse(double memoryUse) {
			this.memoryUse = memoryUse;
			return this;
		}

		public Builder memoryFree(double memoryFree) {
			this.memoryFree = memoryFree;
			return this;
		}

		public Builder swapUse(double swapUse) {
			this.swapUse = swapUse;
			return this;
		}

		public Builder swapFree(double swapFree) {
			this.swapFree = swapFree;
			return this;
		}

		@Override
		public MemoryInfo build() {
			return new MemoryInfo(this);
		}

	}

	public String getTime() {
		return time;
	}

	public double getMemoryUse() {
		return memoryUse;
	}

	public double getMemoryFree() {
		return memoryFree;
	}

	public double getSwapUse() {
		return swapUse;
	}

	public double getSwapFree() {
		return swapFree;
	}
}
