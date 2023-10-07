package com.music.music.model;

import jakarta.persistence.*;

@Entity
@Table(name = "playlistSongsEC")
public class PlaylistSongsModel {

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "playlistSongsID")
	private long playlistSongsID;

    @Column(name = "playlistID")
	private long playlistID;

    @Column(name = "songID")
	private long songID;

	public Long getPlaylistID() {
		return playlistID;
	}

	public void setPlaylistID(Long playlistID) {
		this.playlistID = playlistID;
	}

	public Long getSongID() {
		return songID;
	}

	public void setSongID(long songID) {
		this.songID = songID;
	}

}
