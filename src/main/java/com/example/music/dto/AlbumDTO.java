package com.example.music.dto;

import com.example.music.model.Album;

public class AlbumDTO {

    private Long id;
    private String title;
    private Integer released;
    private String artistFullName;

    public AlbumDTO() {
    }

    public AlbumDTO(Album album) {
        this.id = album.getId();
        this.title = album.getTitle();
        this.released = album.getReleased();
        this.artistFullName = album.getArtist().getFirstName() + " " + album.getArtist().getLastName();
    }

    public Long getId(){
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getReleased() {
        return released;
    }

    public String getArtistFullName() {
        return artistFullName;
    }
}
