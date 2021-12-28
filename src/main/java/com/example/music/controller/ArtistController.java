package com.example.music.controller;

import com.example.music.dto.AlbumDTO;
import com.example.music.dto.ArtistDTO;
import com.example.music.dto.SongDTO;
import com.example.music.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/artist")
public class ArtistController {

    private final ArtistService artistService;

    @Autowired
    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping("/all")
    public List<ArtistDTO> getAll(){
        return artistService.getAll();
    }

    @GetMapping("/{id}")
    public ArtistDTO getById(@PathVariable Long id){
        return artistService.getById(id);
    }

    @GetMapping("/getAllSongsOfArtist/{id}")
    public List<SongDTO> getAllSongsOfArtistById(@PathVariable Long id){
        return artistService.getAllSongsOfArtistById(id);
    }

    @GetMapping("/getByFullName/{fullName}")
    public ArtistDTO getByFullName(@PathVariable String fullName){
        return artistService.getByFullName(fullName);
    }

    @GetMapping("/getAllAlbumsOfArtist/{id}")
    public List<AlbumDTO> getAllAlbumsOfArtistById(@PathVariable Long id){
        return artistService.getAllAlbumsOfArtistById(id);
    }

    @PostMapping("/create")
    public void save(@RequestBody ArtistDTO artist){
        artistService.save(artist);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id){
        artistService.deleteById(id);
    }

    @PutMapping("/update")
    public void update(@RequestBody ArtistDTO artist){
        artistService.update(artist);
    }

}
