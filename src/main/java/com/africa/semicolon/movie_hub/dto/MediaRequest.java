package com.africa.semicolon.movie_hub.dto;

import com.africa.semicolon.movie_hub.model.Category;
import com.africa.semicolon.movie_hub.model.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MediaRequest {
    private Long id;
    private MultipartFile file;
    private String description;
    private Category category;
    private LocalDateTime timeCreated;
    private LocalDateTime timeUpdated;
    private Long userId;
}

