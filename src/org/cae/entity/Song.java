package org.cae.entity;

public class Song {

	private String songId;
	private String songName;
	private String songSellTime;
	private String songOwner;
	private String songCover;
	public Song(){}
	public Song(String songId){
		this.songId=songId;
	}
	public String getSongId() {
		return songId;
	}
	public void setSongId(String songId) {
		this.songId = songId;
	}
	public String getSongName() {
		return songName;
	}
	public void setSongName(String songName) {
		this.songName = songName;
	}
	public String getSongSellTime() {
		return songSellTime;
	}
	public void setSongSellTime(String songSellTime) {
		this.songSellTime = songSellTime;
	}
	public String getSongOwner() {
		return songOwner;
	}
	public void setSongOwner(String songOwner) {
		this.songOwner = songOwner;
	}
	public String getSongCover() {
		return songCover;
	}
	public void setSongCover(String songCover) {
		this.songCover = songCover;
	}
	
}
