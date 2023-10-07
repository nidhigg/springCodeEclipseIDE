package com.music.music.model;

import java.sql.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "playlistsEC")
public class PlaylistModel {
    public PlaylistModel() {
	}

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PlaylistID",columnDefinition = "serial")
	private long playlistID;

    @Column(name = "PlaylistTitle")
	private String playlistTitle;

	public String getPlaylistTitle() {
		return playlistTitle;
	}

	public void setPlaylistTitle(String playlistTitle) {
		this.playlistTitle = playlistTitle;
	}
}
