package com.africa.semicolon.movie_hub.dto;

import com.africa.semicolon.movie_hub.model.Category;
import com.africa.semicolon.movie_hub.model.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MediaResponse {
    private Long id;
    private String url;
    private String description;
    private Category category;
    private LocalDateTime timeCreated;
    private LocalDateTime timeUpdated;
    private UserResponse uploader;
}
