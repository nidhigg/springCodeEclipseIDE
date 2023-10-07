package com.music.music.repository;

import java.util.List;
import java.util.Optional;

import com.music.music.model.SongModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface songRepository extends JpaRepository<SongModel, Long> {

    List<SongModel> findByTitle(String title);

    //Optional<SongModel> findById(Long id);

    //Optional<SongModel> findById(Long id);
}
