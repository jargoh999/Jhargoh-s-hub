package com.africa.semicolon.movie_hub.Security.config.services;

import com.africa.semicolon.movie_hub.Security.config.model.SecureUser;
import com.africa.semicolon.movie_hub.Services.impls.UserServicesImpl;
import com.africa.semicolon.movie_hub.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserServicesImpl userServices;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try{

            User user = userServices.getUserByUserName(username);
            return new SecureUser(user);
        }catch (RuntimeException runtimeException){
            throw new UsernameNotFoundException(runtimeException.getMessage());
        }
    }
}
