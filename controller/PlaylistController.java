package com.music.music.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.music.music.model.PlaylistModel;
import com.music.music.model.PlaylistSongsModel;
import com.music.music.model.SongModel;
import com.music.music.model.SongResponse;
import com.music.music.repository.PlaylistSongRepository;
import com.music.music.repository.playlistRepository;
import com.music.music.repository.songRepository;

import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class PlaylistController {
    
    @Autowired
    private playlistRepository playlistRepository;
    @Autowired
    private songRepository songRepository;
    @Autowired
    private PlaylistSongRepository playlistSongRepository;

    @GetMapping("/v1/stats/playlist")
    public ResponseEntity<Integer> getCountOfAllPlaylist(){
        return new ResponseEntity<Integer>(playlistRepository.findAll().size(),HttpStatus.OK);
    }

    @PostMapping("/v1/playlist")
    public ResponseEntity<PlaylistModel> createPlaylist(@RequestBody PlaylistModel playlistRequest, HttpServletRequest httpServletRequest){
        PlaylistModel playlistModel = new PlaylistModel();
        if (playlistRequest!=null){
            List<PlaylistModel> existingPlaylist = playlistRepository.findByPlaylistTitle(playlistRequest.getPlaylistTitle());
            if(existingPlaylist.size()!=0){
                return new ResponseEntity<>(existingPlaylist.get(0), HttpStatus.ALREADY_REPORTED);
            }else{
                playlistModel.setPlaylistTitle(playlistRequest.getPlaylistTitle());
                return new ResponseEntity<>(playlistRepository.save(playlistModel), HttpStatus.OK);
            }
        }
        return new ResponseEntity<PlaylistModel>(playlistModel, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/v1/playlist/{pid}/{sid}")
    public ResponseEntity<Boolean> addSongToPlaylist(@PathVariable("pid") Long pid, @PathVariable("sid") Long sid){
        PlaylistSongsModel playlistSongsModel = new PlaylistSongsModel();
        if(pid!=null && sid!=null){
            if(playlistRepository.findById(pid).isPresent()){
                if(songRepository.findById(sid).isPresent()){
                    if(playlistSongRepository.findByPlaylistIDAndSongID(pid,sid)!=null){
                        return new ResponseEntity<Boolean>(false, HttpStatus.ALREADY_REPORTED);
                    }else{
                        playlistSongsModel.setPlaylistID(pid);
                        playlistSongsModel.setSongID(sid);
                        playlistSongRepository.save(playlistSongsModel);
                        return new ResponseEntity<Boolean>(true, HttpStatus.OK);

                    }
                }
            }
        }
        return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/v1/playlist/{pid}/{sid}")
    public ResponseEntity<Boolean> deleteSongFromPlaylist(@PathVariable("pid") Long pid, @PathVariable("sid") Long sid){
        if(pid!=null && sid!=null){
            if(playlistSongRepository.findByPlaylistIDAndSongID(pid,sid)!=null){
                PlaylistSongsModel playlistSongsModel = playlistSongRepository.findByPlaylistIDAndSongID(pid,sid);
                playlistSongRepository.delete(playlistSongsModel);
                return new ResponseEntity<Boolean>(true, HttpStatus.OK);
            }  
        }
        return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
    }
    
    @GetMapping("/v1/playlist/{pid}")
    public ResponseEntity<List<String>> getSongsURLbyPlaylistID(@PathVariable("pid") Long pid){
        List<String> links = new ArrayList<>();
        if(pid!=null){
            List<PlaylistSongsModel> existingPlaylistSongModel = playlistSongRepository.findByPlaylistID(pid);
            if(existingPlaylistSongModel != null){
                for(PlaylistSongsModel playlistSongsModel : existingPlaylistSongModel){
                    SongModel songModel = songRepository.getReferenceById(playlistSongsModel.getSongID());
                    links.add(songModel.getYoutubeLink());
                }
                        return new ResponseEntity<List<String>>(links, HttpStatus.OK);
            }   
        }
        return new ResponseEntity<List<String>>(links, HttpStatus.BAD_REQUEST);
    }
    
    @GetMapping("/v1/playlistSongs/{pid}")
    public ResponseEntity<List<SongResponse>> getSongsURLbyPlaySongslistID(@PathVariable("pid") Long pid){
        List<SongResponse> links = new ArrayList<SongResponse>();
        if(pid!=null){
            List<PlaylistSongsModel> existingPlaylistSongModel = playlistSongRepository.findByPlaylistID(pid);
            if(existingPlaylistSongModel != null){
                for(PlaylistSongsModel playlistSongsModel : existingPlaylistSongModel){
                    SongModel songModel = songRepository.getReferenceById(playlistSongsModel.getSongID());
                    SongResponse song = new SongResponse();
                    song.setTitle(songModel.getTitle());
                    song.setUrl(songModel.getYoutubeLink());
                    links.add(song);
                }
                        return new ResponseEntity<List<SongResponse>>(links, HttpStatus.OK);
            }   
        }
        return new ResponseEntity<List<SongResponse>>(links, HttpStatus.BAD_REQUEST);
    }


}
