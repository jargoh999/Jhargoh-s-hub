package com.africa.semicolon.movie_hub.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
public class MediaRequest {
    private Long id;
    private MultipartFile file;
    private String description;
}
