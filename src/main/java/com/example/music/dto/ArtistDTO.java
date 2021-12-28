package com.example.music.dto;

import com.example.music.model.Artist;

import java.time.LocalDate;

public class ArtistDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;

    public ArtistDTO() {
    }

    public ArtistDTO(Artist artist) {
        this.id = artist.getId();
        this.firstName = artist.getFirstName();
        this.lastName = artist.getLastName();
        this.birthDate = artist.getBirthDate();
    }

    public ArtistDTO(Long id, String firstName, String lastName, LocalDate birthDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public Long getId(){
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

}
