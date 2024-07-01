package com.africa.semicolon.movie_hub.Security.config;

import com.africa.semicolon.movie_hub.Security.config.filter.CustomAuthorizationFilter;
import com.africa.semicolon.movie_hub.Security.config.filter.CustomUsernamePasswordAuthenticationFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import static org.springframework.http.HttpMethod.POST;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final AuthenticationManager authenticationManager;
    private final CustomAuthorizationFilter customAuthorizationFilter;
    @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        var  authFilter = new CustomUsernamePasswordAuthenticationFilter(authenticationManager) ;
        authFilter.setFilterProcessesUrl("/api/v1/auth");
        return httpSecurity.csrf(AbstractHttpConfigurer::disable)
                       .cors(AbstractHttpConfigurer::disable)
                       .sessionManagement(c->c.sessionCreationPolicy(STATELESS))
                       .addFilterAt(authFilter, BasicAuthenticationFilter.class)
                        .addFilterBefore(customAuthorizationFilter,CustomUsernamePasswordAuthenticationFilter.class)
                       .authorizeHttpRequests(
                               requestEndPoint->
                                       requestEndPoint.requestMatchers("/api/v1/auth").permitAll()
                                       .requestMatchers("/api/v1/media").hasAnyAuthority("USER")

                       )
                       .build();
    }
}
