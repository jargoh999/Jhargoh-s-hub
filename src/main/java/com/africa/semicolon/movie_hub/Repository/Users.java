package com.africa.semicolon.movie_hub.Repository;

import com.africa.semicolon.movie_hub.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Users extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String username);
    Optional<User> findByUserId(Long userId);
}
