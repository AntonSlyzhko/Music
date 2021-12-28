package com.example.music.dto;

import com.example.music.model.Song;

import java.time.LocalTime;

public class SongDTO {

    private Long id;
    private String name;
    private String artistFullName;
    private LocalTime duration;
    private Long albumId;

    public SongDTO() {
    }

    public SongDTO(String name, LocalTime duration, Long albumId) {
        this.name = name;
        this.duration = duration;
        this.albumId = albumId;
    }

    public SongDTO(Song song) {
        this.id = song.getId();
        this.name = song.getName();
        this.duration = song.getDuration();
        this.albumId = song.getAlbum().getId();
        this.artistFullName = song.getAlbum().getArtist().getFirstName() + " " + song.getAlbum().getArtist().getLastName();
    }

    public String getName() {
        return name;
    }

    public void setArtistFullName(String artistFullName){
        this.artistFullName = artistFullName;
    }

    public Long getId(){
        return id;
    }

    public String getArtistFullName() {
        return artistFullName;
    }

    public LocalTime getDuration() {
        return duration;
    }

    public Long getAlbumId(){
        return albumId;
    }
}
