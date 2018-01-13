package org.cae.monitor.entity;

import top.starrysea.kql.entity.Entity;
import top.starrysea.kql.entity.IBuilder;

public class JVMMemory implements Entity {

	private String time;
	private double edenRate;
	private double survivorRate;
	private double oldGenRate;
	private double permGenRate;

	private JVMMemory(Builder builder) {
		this.time = builder.time;
		this.edenRate = builder.edenRate;
		this.survivorRate = builder.survivorRate;
		this.oldGenRate = builder.oldGenRate;
		this.permGenRate = builder.permGenRate;
	}

	public static class Builder implements IBuilder<JVMMemory> {

		private String time;
		private double edenRate;
		private double survivorRate;
		private double oldGenRate;
		private double permGenRate;

		public Builder time(String time) {
			this.time = time;
			return this;
		}

		public Builder edenRate(double edenRate) {
			this.edenRate = edenRate;
			return this;
		}

		public Builder survivorRate(double survivorRate) {
			this.survivorRate = survivorRate;
			return this;
		}

		public Builder oldGenRate(double oldGenRate) {
			this.oldGenRate = oldGenRate;
			return this;
		}

		public Builder permGenRate(double permGenRate) {
			this.permGenRate = permGenRate;
			return this;
		}

		@Override
		public JVMMemory build() {
			return new JVMMemory(this);
		}

	}

	public String getTime() {
		return time;
	}

	public double getEdenRate() {
		return edenRate;
	}

	public double getSurvivorRate() {
		return survivorRate;
	}

	public double getOldGenRate() {
		return oldGenRate;
	}

	public double getPermGenRate() {
		return permGenRate;
	}
}
