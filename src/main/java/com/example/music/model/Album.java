package com.example.music.model;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "album")
public class Album {

    @Id
    @SequenceGenerator(
            name = "album_sequence",
            sequenceName = "album_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "album_sequence"
    )
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id", nullable = false)
    private Artist artist;

    private String title;

    private Integer released;

    @OneToMany(mappedBy = "album", orphanRemoval = true)
    private List<Song> songs = new LinkedList<>();

    public Album() {
    }

    public Album(Artist artist,
                 String title,
                 Integer released) {
        this.artist = artist;
        this.title = title;
        this.released = released;
    }

    public Long getId() {
        return id;
    }

    public Artist getArtist() {
        return artist;
    }

    public String getTitle() {
        return title;
    }

    public Integer getReleased() {
        return released;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setReleased(Integer released) {
        this.released = released;
    }
}
