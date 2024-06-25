package com.africa.semicolon.movie_hub.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
public class LoginRequest {
    private String username;
    private String password;
}
