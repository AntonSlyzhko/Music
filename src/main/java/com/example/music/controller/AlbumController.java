package com.example.music.controller;

import com.example.music.dto.AlbumDTO;
import com.example.music.dto.SongDTO;
import com.example.music.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/album")
public class AlbumController {

    private final AlbumService albumService;

    @Autowired
    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping("/all")
    public List<AlbumDTO> getAll(){
        return albumService.findAll();
    }

    @GetMapping("/{id}")
    public AlbumDTO getById(@PathVariable Long id){
        return albumService.getById(id);
    }

    @GetMapping("/getAllSongsOfAlbum/{id}")
    public List<SongDTO> getAllSongsOfAlbumById(@PathVariable Long id){
        return  albumService.getAllSongsOfAlbumById(id);
    }

    @PostMapping("/create")
    public void save(@RequestBody AlbumDTO album){
        albumService.save(album);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id){
        albumService.deleteById(id);
    }

    @PutMapping("/update")
    public void update(@RequestBody AlbumDTO album){
        albumService.update(album);
    }
}
