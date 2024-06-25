package com.africa.semicolon.movie_hub.Security.config.manager;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
@Configuration
@RequiredArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager {

   private final AuthenticationProvider authenticationProvider;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
         Class<? extends Authentication> authenticationType = authentication.getClass();
         if(authenticationProvider.supports(authenticationType)){
             return authenticationProvider.authenticate(authentication);
         }
         throw new BadCredentialsException("oops");
    }
}
