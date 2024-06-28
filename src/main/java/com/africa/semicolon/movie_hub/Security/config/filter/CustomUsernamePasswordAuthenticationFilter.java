package com.africa.semicolon.movie_hub.Security.config.filter;
import com.africa.semicolon.movie_hub.dto.LoginRequest;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;



@RequiredArgsConstructor
public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {


                 try{
                     //1. retrieve authentication credentials from the request body
                     InputStream requestBodyStream = request.getInputStream();
                     //2. convert the json data from 1 to java object (LoginRequest)
                     LoginRequest loginRequest = objectMapper.readValue(requestBodyStream, LoginRequest.class);
                     //3. create an authentication object that is to be authenticated
                     //4. authenticate the authentication object using the authentication manager
                     //5. return the authentication object to the authentication manager
                     //6. return the authentication object to the filter chain
                     var authResult = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
                     //7. set the authentication object to the security context holder
                     SecurityContextHolder.getContext().setAuthentication(authResult);
                     return authResult;
                 } catch (IOException e) {
                     throw new BadCredentialsException(e.getMessage());
                 }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String token=JWT.create()
                .withIssuer("mavericks hub")
                .withArrayClaim("roles",getClaimsFrom(authResult.getAuthorities()))
                .withExpiresAt(Instant.now().plusSeconds(24*60*60))
                .sign(Algorithm.HMAC512("secret"));
        Map<String, String> result = new HashMap<>();
        result.put("access_token", token);
        response.getOutputStream().write(objectMapper.writeValueAsBytes(result));
        response.flushBuffer();
        chain.doFilter(request, response);
    }


    private static String[] getClaimsFrom(Collection<? extends GrantedAuthority> authorities){
           return authorities.stream()
                   .map(GrantedAuthority::getAuthority)
                   .toArray(String[]::new);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        super.unsuccessfulAuthentication(request, response, failed);
    }
}
