package com.africa.semicolon.movie_hub.Repository;

import com.africa.semicolon.movie_hub.model.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaRepo extends JpaRepository<Media ,Long> {
    Media findMediaById(Long id);
    Media findMediaByDescription(String description);
}

