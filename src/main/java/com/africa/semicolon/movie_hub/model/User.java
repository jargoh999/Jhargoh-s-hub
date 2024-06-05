package com.africa.semicolon.movie_hub.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(unique = true)
    private String email;
    private String userPassword;
    @Setter(AccessLevel.NONE)
    private LocalDateTime timeCreated;
    @Setter(AccessLevel.NONE)
    private LocalDateTime timeUpdated;
    @PrePersist
    public void setTimeCreated() {
        this.timeCreated = LocalDateTime.now();
    }
    @PreUpdate
    public void setTimeUpdated() {
        this.timeCreated = LocalDateTime.now();
    }


}
