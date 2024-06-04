package com.africa.semicolon.movie_hub.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MediaResponse {
    private Long id;
    private String url;
    private String description;
    private String message;
}
