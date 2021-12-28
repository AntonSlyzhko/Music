package com.example.music.service;

import com.example.music.dto.AlbumDTO;
import com.example.music.dto.ArtistDTO;
import com.example.music.dto.SongDTO;
import com.example.music.exceptions.ArtistAlreadyExistsException;
import com.example.music.exceptions.ArtistHasAlbumsException;
import com.example.music.exceptions.ArtistNotFoundException;
import com.example.music.model.Album;
import com.example.music.model.Artist;
import com.example.music.model.Song;
import com.example.music.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArtistService {
    private final ArtistRepository artistRepository;

    @Autowired
    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public ArtistDTO getById(Long id){
        return new ArtistDTO(artistRepository.getById(id));
    }

    public List<ArtistDTO> getAll(){
        return artistRepository.findAll().stream()
                .map(ArtistDTO::new)
                .collect(Collectors.toList());
    }

    public ArtistDTO getByFullName(String fullName){
        Optional<Artist> artist = artistRepository.findArtistByFullName(fullName);
        if (!artist.isPresent())
            throw new ArtistNotFoundException();
        return new ArtistDTO(artist.get());
    }

    public List<SongDTO> getAllSongsOfArtistById(Long id){
        Artist artist = artistRepository.getById(id);
        List<Song> songs = new LinkedList<>();
        artist.getAlbums().forEach(a -> songs.addAll(a.getSongs()));
        return songs.stream().map(SongDTO::new).collect(Collectors.toList());
    }

    public List<AlbumDTO> getAllAlbumsOfArtistById(Long id){
        Artist artist = artistRepository.getById(id);
        List<Album> albums =artist.getAlbums();
        return albums.stream().map(AlbumDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public void deleteById(Long id){
        Artist artist = artistRepository.getById(id);
        if(artist.getAlbums().isEmpty())
            artistRepository.deleteById(id);
        else
            throw new ArtistHasAlbumsException();
    }

    @Transactional
    public void update(ArtistDTO artist){
        Artist goesArtist = artistRepository.getById(artist.getId());
        goesArtist.setFirstName(artist.getFirstName());
        goesArtist.setLastName(artist.getLastName());
        goesArtist.setBirthDate(artist.getBirthDate());
        artistRepository.save(goesArtist);
    }

    @Transactional
    public void save(ArtistDTO artist){
        Optional<Artist> optionalArtist = artistRepository.findArtistByFullName(artist.getFirstName()+ " " + artist.getLastName());
        if(optionalArtist.isPresent())
            throw new ArtistAlreadyExistsException();
        Artist newArtist = new Artist(artist.getFirstName(),
                                      artist.getLastName(),
                                      artist.getBirthDate());
        artistRepository.save(newArtist);
    }

}
