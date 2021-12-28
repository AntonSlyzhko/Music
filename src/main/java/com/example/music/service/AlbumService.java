package com.example.music.service;

import com.example.music.dto.AlbumDTO;
import com.example.music.dto.SongDTO;
import com.example.music.exceptions.AlbumContainsSongsException;
import com.example.music.exceptions.ArtistNotFoundException;
import com.example.music.model.Album;
import com.example.music.model.Artist;
import com.example.music.repository.AlbumRepository;
import com.example.music.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final ArtistRepository artistRepository;

    @Autowired
    public AlbumService(AlbumRepository albumRepository, ArtistRepository artistRepository) {
        this.albumRepository = albumRepository;
        this.artistRepository = artistRepository;
    }

    public AlbumDTO getById(Long id){
        return new AlbumDTO(albumRepository.getById(id));
    }

    public List<AlbumDTO> findAll(){
        return albumRepository.findAll().stream().map(AlbumDTO::new).collect(Collectors.toList());
    }


    public List<SongDTO> getAllSongsOfAlbumById(Long id){
        Album album = albumRepository.getById(id);
        return album.getSongs().stream()
                .map(SongDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteById(Long id){
        Album album = albumRepository.getById(id);
        if(album.getSongs().isEmpty())
            albumRepository.deleteById(id);
        else
            throw new AlbumContainsSongsException();
    }

    @Transactional
    public void update(AlbumDTO album){
        Optional<Artist> artist = artistRepository.findArtistByFullName(album.getArtistFullName());
        if(!artist.isPresent())
            throw new ArtistNotFoundException();
        Album goesAlbum = albumRepository.getById(album.getId());
        goesAlbum.setArtist(artist.get());
        goesAlbum.setTitle(album.getTitle());
        goesAlbum.setReleased(album.getReleased());
        albumRepository.save(goesAlbum);
    }

    @Transactional
    public void save(AlbumDTO album){
        Optional<Artist> optionalArtist = artistRepository.findArtistByFullName(album.getArtistFullName());
        if(!optionalArtist.isPresent())
            throw new ArtistNotFoundException();
        Optional<Album> optionalAlbum = albumRepository.findAlbumByTitleAndReleasedAndArtistId(album.getTitle(),
                                                                                               album.getReleased(),
                                                                                               optionalArtist.get().getId());
        if(optionalAlbum.isPresent())
            throw new IllegalStateException("album already exists");
        Album albumToSave = new Album(optionalArtist.get(),
                                      album.getTitle(),
                                      album.getReleased());
        albumRepository.save(albumToSave);
    }
}
