package com.music.music.repository;

import com.music.music.model.PlaylistSongsModel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistSongRepository extends JpaRepository<PlaylistSongsModel, Long> {
    
    @Query("select u from PlaylistSongsModel u where u.playlistID=?1 and u.songID=?2")
    PlaylistSongsModel findByPlaylistIDAndSongID(Long pid, Long sid);

    List<PlaylistSongsModel> findByPlaylistID(Long pid);
}
