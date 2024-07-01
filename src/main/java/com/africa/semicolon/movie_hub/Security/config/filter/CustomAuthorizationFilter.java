package com.africa.semicolon.movie_hub.Security.config.filter;

import com.africa.semicolon.movie_hub.Security.config.utils.SecurityUtils;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

import static com.africa.semicolon.movie_hub.Security.config.utils.SecurityUtils.JWT_PREFIX;
import static com.africa.semicolon.movie_hub.Security.config.utils.SecurityUtils.PUBLIC_ENDPOINTS;
@Configuration
public class CustomAuthorizationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        /**
         * 1a. retrieve request path
             * 1b. if request path from 1a is a public path
             * 1c. call the next filter in chain and terminate this filters
             * execution
         * 2. Retrieve access token from request header
         * 3. Decode access token
         * 4. extract token permission
         * 5. add token permission to security context
         * 6. call the next filter in the filter chain
         */

        String requestPath = request.getServletPath();// "api/v1....."
        boolean isRequestPathPublic = PUBLIC_ENDPOINTS.contains(requestPath);
        if(isRequestPathPublic)filterChain.doFilter(request, response);
        String authHeader = request.getHeader("AUTHORIZATION");
        if(authHeader != null) {
            String token = authHeader.substring(JWT_PREFIX.length()).strip();
            var verifier = JWT.require(Algorithm.HMAC512("secret".getBytes()))
                    .withIssuer("mavericks hub")
                    .withClaimPresence("roles")
                    .build();
            DecodedJWT decodedJWT = verifier.verify(token);
            List<SimpleGrantedAuthority> authorities =
                    decodedJWT.getClaim("roles").asList(SimpleGrantedAuthority.class);
            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(null, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }
}
