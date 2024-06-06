package com.africa.semicolon.movie_hub.Repository;

import com.africa.semicolon.movie_hub.model.Category;
import com.africa.semicolon.movie_hub.model.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MediaRepo extends JpaRepository<Media ,Long> {
    Optional<Media>  findMediaById(Long id);
    Optional<Media>  findMediaByDescription(String description);
    List<Media> findMediaByCategory(Category category);

    @Query("SELECT m FROM Media m where m.uploader.userId=:userId")
    List<Media> findAllMediaFor(Long userId);
}

