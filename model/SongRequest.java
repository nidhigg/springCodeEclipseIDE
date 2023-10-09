package com.music.music.model;

import lombok.AllArgsConstructor;

import lombok.Data;

import lombok.NoArgsConstructor;

import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SongRequest {

    private String title;
    private String genre;
    private int yearOfRelease;
    private String albumName;

    public String getTitle() {
        return title;
    }

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

}
