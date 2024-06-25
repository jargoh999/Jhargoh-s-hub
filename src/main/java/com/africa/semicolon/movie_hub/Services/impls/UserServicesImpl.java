package com.africa.semicolon.movie_hub.Services.impls;

import com.africa.semicolon.movie_hub.Repository.UserRepository;
import com.africa.semicolon.movie_hub.Services.UserServices;
import com.africa.semicolon.movie_hub.dto.UserRequest;
import com.africa.semicolon.movie_hub.dto.UserResponse;
import com.africa.semicolon.movie_hub.model.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServicesImpl implements UserServices {
    private final ModelMapper mapper;
    private final UserRepository users;


    @Override
    public UserResponse addUser(UserRequest request) {
        User user =mapper.map(request, User.class);
        users.save(user);
        return mapper.map(user, UserResponse.class);
    }

    @Override
    public User getUserBy(Long id) {
        return users.findUserByUserId(id).
         orElseThrow(()->new RuntimeException("User not found"));
    }

    @Override
    public User getUserByUserName(String username) {
        return users.findUserByEmailIgnoreCase(username)
                .orElseThrow(()->new RuntimeException("User not found"));
    }
}
