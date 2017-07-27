package org.cae.entity;

public class Song extends Entity {

	private String songId;
	private String songName;
	private String songSellTime;
	private String songOwner;
	private String songCover;
	private String songCreateTime;
	private String songLastModifyTime;
	private Integer songClick;
	private Short songVideo;

	private Song(Builder builder) {
		this.songId = builder.songId;
		this.songName = builder.songName;
		this.songSellTime = builder.songSellTime;
		this.songOwner = builder.songOwner;
		this.songCover = builder.songCover;
		this.songCreateTime = builder.songCreateTime;
		this.songLastModifyTime = builder.songLastModifyTime;
		this.songClick = builder.songClick;
		this.songVideo = builder.songVideo;
	}

	public static class Builder implements IBuilder<Song> {
		private String songId;
		private String songName;
		private String songSellTime;
		private String songOwner;
		private String songCover;
		private String songCreateTime;
		private String songLastModifyTime;
		private Integer songClick;
		private Short songVideo;

		public Builder() {
		}

		public Builder songId(String songId) {
			this.songId = songId;
			return this;
		}

		public Builder songName(String songName) {
			this.songName = songName;
			return this;
		}

		public Builder songSellTime(String songSellTime) {
			this.songSellTime = songSellTime;
			return this;
		}

		public Builder songOwner(String songOwner) {
			this.songOwner = songOwner;
			return this;
		}

		public Builder songCover(String songCover) {
			this.songCover = songCover;
			return this;
		}

		public Builder songCreateTime(String songCreateTime) {
			this.songCreateTime = songCreateTime;
			return this;
		}

		public Builder songLastModifyTime(String songLastModifyTime) {
			this.songLastModifyTime = songLastModifyTime;
			return this;
		}

		public Builder songClick(Integer songClick) {
			this.songClick = songClick;
			return this;
		}

		public Builder songVideo(Short songVideo) {
			this.songVideo = songVideo;
			return this;
		}

		@Override
		public Song build() {
			return new Song(this);
		}
	}

	public Short getSongVideo() {
		return songVideo;
	}

	public String getSongId() {
		return songId;
	}

	public String getSongName() {
		return songName;
	}

	public String getSongSellTime() {
		return songSellTime;
	}

	public String getSongOwner() {
		return songOwner;
	}

	public String getSongCover() {
		return songCover;
	}

	public void setSongCover(String songCover) {
		this.songCover = songCover;
	}

	public String getSongCreateTime() {
		return songCreateTime;
	}

	public String getSongLastModifyTime() {
		return songLastModifyTime;
	}

	public Integer getSongClick() {
		return songClick;
	}

}
