package com.example.music.controller;

import com.example.music.dto.SongDTO;
import com.example.music.exceptions.SongNotFoundException;
import com.example.music.model.Song;
import com.example.music.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(path = "api/v1/song")
public class SongController {

    private final SongService songService;

    @Autowired
    public SongController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping("/all")
    public List<SongDTO> getAll(){
        return songService.findAll();
    }

    @GetMapping("/{id}")
    public SongDTO getById(@PathVariable Long id){
        try {
            return songService.getById(id);
        }catch (NoSuchElementException e){
            throw new SongNotFoundException();
        }
    }

    @PostMapping("/create")
    public void save(@RequestBody SongDTO song){
        songService.save(song);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id){
        songService.deleteById(id);
    }

    @PutMapping("/update")
    public void update(@RequestBody SongDTO song){
        songService.update(song);
    }


}
