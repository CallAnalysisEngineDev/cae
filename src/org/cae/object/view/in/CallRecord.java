package org.cae.object.view.in;

import org.cae.object.view.in.Song;

public class CallRecord {

	private Song song;
	private String callId;
	private String callSource;
	private Short callVersion;

	public Song getSong() {
		return song;
	}

	public void setSong(Song song) {
		this.song = song;
	}

	public String getCallId() {
		return callId;
	}

	public void setCallId(String callId) {
		this.callId = callId;
	}

	public String getCallSource() {
		return callSource;
	}

	public void setCallSource(String callSource) {
		this.callSource = callSource;
	}

	public Short getCallVersion() {
		return callVersion;
	}

	public void setCallVersion(Short callVersion) {
		this.callVersion = callVersion;
	}

	public org.cae.object.dto.CallRecord toBo() {
		return new org.cae.object.dto.CallRecord.Builder().callId(callId)
				.callSource(callSource).callVersion(callVersion)
				.song(song.toBo()).build();
	}
}
