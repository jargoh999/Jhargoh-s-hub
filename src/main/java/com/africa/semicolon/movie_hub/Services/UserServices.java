package com.africa.semicolon.movie_hub.Services;

import com.africa.semicolon.movie_hub.dto.UserRequest;
import com.africa.semicolon.movie_hub.dto.UserResponse;
import com.africa.semicolon.movie_hub.model.User;

public interface UserServices {
    UserResponse createUser (UserRequest request);

    User findUserBy(Long id);
}
