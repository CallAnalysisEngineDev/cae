package org.cae.object.dto;

import org.cae.common.Generator;

import top.starrysea.kql.entity.Entity;
import top.starrysea.kql.entity.IBuilder;

public class CallRecord implements Entity {

	private Song song;
	private String callId;
	private String callSource;
	private Short callVersion;

	private CallRecord(Builder builder) {
		this.song = builder.song;
		this.callId = builder.callId;
		this.callSource = builder.callSource;
		this.callVersion = builder.callVersion;
	}

	public static class Builder implements IBuilder<CallRecord> {
		private Song song;
		private String callId;
		private String callSource;
		private Short callVersion;

		public Builder() {
		}

		public Builder song(Song song) {
			this.song = song;
			return this;
		}

		public Builder callId(String callId) {
			this.callId = callId;
			return this;
		}

		public Builder callSource(String callSource) {
			this.callSource = callSource;
			return this;
		}

		public Builder callVersion(Short callVersion) {
			this.callVersion = callVersion;
			return this;
		}

		@Override
		public CallRecord build() {
			return new CallRecord(this);
		}

	}

	public Song getSong() {
		if(song==null)
			return Generator.song();
		return song;
	}

	public String getCallId() {
		return callId;
	}

	public String getCallSource() {
		return callSource;
	}

	public Short getCallVersion() {
		return callVersion;
	}

}
