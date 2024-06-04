package com.africa.semicolon.movie_hub.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResponse {
    private Long userId;
    private String email;
    private String userPassword;
}
