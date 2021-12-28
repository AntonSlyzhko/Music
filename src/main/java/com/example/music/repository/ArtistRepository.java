package com.example.music.repository;

import com.example.music.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {

    @Query(value = "SELECT a FROM Artist a WHERE lower(concat(a.firstName, ' ', a.lastName)) LIKE concat('%', lower(?1), '%')")
    Optional<Artist> findArtistByFullName(String fullName);

}
