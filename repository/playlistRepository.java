package com.music.music.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.music.music.model.PlaylistModel;

public interface playlistRepository extends JpaRepository<PlaylistModel, Long> {
     
    List<PlaylistModel> findByPlaylistTitle(String playlistTitle);

}
