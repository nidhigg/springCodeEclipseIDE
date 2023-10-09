package com.music.music.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.music.music.model.SongModel;
import com.music.music.model.SongRequest;
import com.music.music.repository.songRepository;

import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class SongController {

    @Autowired
    private songRepository songRepository;

    @GetMapping("/v1/stats/songs")
    public ResponseEntity<Integer> getCountOfAllSongs(){
        return new ResponseEntity<Integer>(songRepository.findAll().size(),HttpStatus.OK);
    }
    
    @PutMapping("/v1/song")
    public ResponseEntity<Boolean> updateSong(@RequestBody SongRequest songRequest, HttpServletRequest httpServletRequest ){
        if (songRequest!=null){
            List<SongModel> existingSongModel = songRepository.findByTitle(songRequest.getTitle());
            if(existingSongModel.size()!=0){
            	existingSongModel.get(0).setGenre(songRequest.getGenre());
            	songRepository.save(existingSongModel.get(0));
                return new ResponseEntity<Boolean>(true, HttpStatus.OK);
            }else{
                return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
    }
    
    @PostMapping("/v1/song")
    public ResponseEntity<SongModel> addSong(@RequestBody SongRequest songRequest, HttpServletRequest httpServletRequest ){
        SongModel songModel = new  SongModel();
        if (songRequest!=null){
            List<SongModel> existingSongModel = songRepository.findByTitle(songRequest.getTitle());
            if(existingSongModel.size()!=0){
                return new ResponseEntity<>(existingSongModel.get(0), HttpStatus.ALREADY_REPORTED);
            }else{
                return new ResponseEntity<>(songRepository.save(creatSongModel(songRequest)), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(songModel, HttpStatus.BAD_REQUEST);
    }
    
    

    private SongModel creatSongModel(SongRequest songRequest){
        return SongModel.builder()
                .title(songRequest.getTitle())
                .Genre(songRequest.getGenre())
                .YearOfRelease(songRequest.getYearOfRelease())
                .AlbumName(songRequest.getAlbumName())
                .build();
    }

    @GetMapping("/v1/stats/songs/{id}")
	public ResponseEntity<SongModel> getSongById(@PathVariable("id") long id) {
		Optional<SongModel> songData = songRepository.findById(id);

		if (songData.isPresent()) {
			return new ResponseEntity<>(songData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

    
}
