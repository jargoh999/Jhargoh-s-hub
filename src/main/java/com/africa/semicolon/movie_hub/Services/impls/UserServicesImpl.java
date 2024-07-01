package com.africa.semicolon.movie_hub.Services.impls;

import com.africa.semicolon.movie_hub.Repository.UserRepository;
import com.africa.semicolon.movie_hub.Services.UserServices;
import com.africa.semicolon.movie_hub.dto.UserRequest;
import com.africa.semicolon.movie_hub.dto.UserResponse;
import com.africa.semicolon.movie_hub.model.Authority;
import com.africa.semicolon.movie_hub.model.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class UserServicesImpl implements UserServices {
    private final ModelMapper mapper;
    private final UserRepository users;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserResponse addUser(UserRequest request) {
        User user =mapper.map(request, User.class);
        user.setUserPassword(passwordEncoder.encode(request.getUserPassword()));
        user.setAuthorities(new HashSet<>());
        var authorities = user.getAuthorities();
        authorities.add(Authority.USER);
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
