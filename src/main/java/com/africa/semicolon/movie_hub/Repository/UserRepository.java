package com.africa.semicolon.movie_hub.Repository;

import com.africa.semicolon.movie_hub.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByUserId(Long id);
    Optional<User> findUserByEmailIgnoreCase(String email);
}
