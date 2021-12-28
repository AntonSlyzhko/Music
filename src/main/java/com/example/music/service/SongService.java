package com.example.music.service;

import com.example.music.dto.SongDTO;
import com.example.music.model.Song;
import com.example.music.repository.AlbumRepository;
import com.example.music.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SongService {

    private final SongRepository songRepository;
    private final AlbumRepository albumRepository;

    @Autowired
    public SongService(SongRepository songRepository, AlbumRepository albumRepository) {
        this.songRepository = songRepository;
        this.albumRepository = albumRepository;
    }

    public SongDTO getById(Long id){
        return new SongDTO(songRepository.getById(id));
    }

    public List<SongDTO> findAll(){
        return songRepository.findAll().stream().map(SongDTO::new).collect(Collectors.toList());
    }

    public void deleteById(Long id){
        songRepository.deleteById(id);
    }

    @Transactional
    public void update(SongDTO song){
        Song oldSong = songRepository.getById(song.getId());
        oldSong.setName(song.getName());
        oldSong.setAlbum(albumRepository.getById(song.getAlbumId()));
        oldSong.setDuration(song.getDuration());
        songRepository.save(oldSong);
    }

    public void save(SongDTO song){
        Optional<Song> optionalSong = songRepository.findSongByNameAndDurationAndAlbumId( song.getName(),
                                                                                        song.getDuration(),
                                                                                        song.getAlbumId());
        if(optionalSong.isPresent())
            throw new IllegalStateException("song already exists");
        Song songToSave = new Song(albumRepository.getById(song.getAlbumId()),
                                    song.getName(),
                                    song.getDuration());
        songRepository.save(songToSave);
    }

}
