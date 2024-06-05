package com.africa.semicolon.movie_hub.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "media")
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;
    private String description;
    @Enumerated(EnumType.STRING)
    private Category category;
    @Setter(AccessLevel.NONE)
    private LocalDateTime timeCreated;
    @Setter(AccessLevel.NONE)
    private LocalDateTime timeUpdated;
    @ManyToOne
    private User user;

    @PrePersist
    private void setTimeCreated(){
        this.timeCreated = LocalDateTime.now();
    }

    @PreUpdate
    public void setTimeUpdated() {
        this.timeCreated = LocalDateTime.now();
    }
}


