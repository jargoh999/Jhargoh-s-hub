package com.africa.semicolon.movie_hub.Repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j

class MediaRepoTest {
    @Autowired
    private MediaRepo mediaRepo;

    @Test

    @Sql(scripts = {"/db/data.sql"})
    void findAllMediaFor() {
        var media =mediaRepo.findAllMediaFor(200L);
        assertThat(media.size()).isEqualTo(3);
    }
}