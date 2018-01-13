package org.cae.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import org.cae.object.dto.Admin;
import org.cae.object.dto.CallRecord;
import org.cae.object.dto.Song;

public class Generator {

	public static <K, V> HashMap<K, V> hashMap() {
		return new HashMap<K, V>();
	}

	public static <K, V> ConcurrentHashMap<K, V> concurrentHashMap() {
		return new ConcurrentHashMap<K, V>();
	}

	public static <T> ArrayList<T> arrayList() {
		return new ArrayList<T>();
	}

	public static Song song() {
		return new Song.Builder().build();
	}

	public static Song song(String songId) {
		return new Song.Builder().songId(songId).build();
	}

	public static CallRecord callRecord() {
		return new CallRecord.Builder().build();
	}

	public static CallRecord callRecord(String callId) {
		return new CallRecord.Builder().callId(callId).build();
	}

	public static Admin admin() {
		return new Admin.Builder().build();
	}

	public static Admin admin(Integer adminId) {
		return new Admin.Builder().adminId(adminId).build();
	}
}
