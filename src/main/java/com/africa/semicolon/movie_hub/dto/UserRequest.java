package com.africa.semicolon.movie_hub.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRequest {
    private Long userId;
    private String email;
    private String userPassword;
    private LocalDateTime timeCreated;
    private LocalDateTime timeUpdated;
}
