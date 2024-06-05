package com.africa.semicolon.movie_hub.Services.impls;

import com.africa.semicolon.movie_hub.Repository.Users;
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
    private final Users users;
    @Override
    public UserResponse createUser(UserRequest request) {

             User user =mapper.map(request, User.class);
             users.save(user);
             return mapper.map(user, UserResponse.class);
    }



    @Override
    public User findUserBy(Long id) {
        return users.findByUserId(id).orElseThrow(()->
                new RuntimeException("user not found"));
    }
}
