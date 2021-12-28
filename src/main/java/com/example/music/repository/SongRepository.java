package com.example.music.repository;

import com.example.music.model.Album;
import com.example.music.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalTime;
import java.util.Optional;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {

    Optional<Song> findSongByNameAndDurationAndAlbumId(String name, LocalTime duration, Long albumId);
}
