package com.africa.semicolon.movie_hub.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResponse {
    private Long userId;
    private String email;
    private String userPassword;
    private LocalDateTime timeCreated;
    private LocalDateTime timeUpdated;
}
