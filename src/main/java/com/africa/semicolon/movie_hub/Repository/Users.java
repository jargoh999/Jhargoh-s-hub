package com.africa.semicolon.movie_hub.Repository;

import com.africa.semicolon.movie_hub.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Users extends JpaRepository<User,Long> {
    User findByEmail(String username);
    User findByUserId(Long userId);
}
