package com.music.music.model;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;

import lombok.Builder;

import lombok.Data;

import lombok.NoArgsConstructor;

import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "songsEC")
public class SongModel{
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "songID",columnDefinition = "serial")
	private long songID;

	@Column(name = "Title",nullable = false)
	private String title;

    @Column(name = "Genre")
	private String Genre;

    @Column(name = "YearOfRelease")
	private int YearOfRelease;

    @Column(name = "AlbumName")
	private String AlbumName;

    @Column(name = "ArtistName")
	private String ArtistName;

    @Column(name = "YoutubeLink")
	private String YoutubeLink;


    public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long getSongID() {
		return songID;
	}

	public void setSongID(long songID) {
		this.songID = songID;
	}

	public String getGenre() {
		return Genre;
	}

	public void setGenre(String genre) {
		this.Genre = genre;
	}

	public int getYearOfRelease() {
		return YearOfRelease;
	}

	public void setYearOfRelease(int yearOfRelease) {
		this.YearOfRelease = yearOfRelease;
	}

	public String getAlbumName() {
		return AlbumName;
	}

	public void setAlbumName(String albumName) {
		this.AlbumName = albumName;
	}

	public String getArtistName() {
		return ArtistName;
	}

	public void setArtistName(String artistName) {
		this.ArtistName = artistName;
	}

	public String getYoutubeLink() {
		return YoutubeLink;
	}

	public void setYoutubeLink(String youtubeLink) {
		this.YoutubeLink = youtubeLink;
	}
}