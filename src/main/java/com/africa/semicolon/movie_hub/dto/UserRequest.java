package com.africa.semicolon.movie_hub.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRequest {
    private Long userId;
    private String email;
    private String userPassword;
}
