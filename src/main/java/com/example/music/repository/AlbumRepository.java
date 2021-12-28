package com.example.music.repository;

import com.example.music.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {

    Optional<Album> findAlbumByTitleAndReleasedAndArtistId(String title,
                                                            Integer released,
                                                            Long id);

}
