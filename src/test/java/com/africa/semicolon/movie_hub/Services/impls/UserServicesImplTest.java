package com.africa.semicolon.movie_hub.Services.impls;

import com.africa.semicolon.movie_hub.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServicesImplTest {
    @Autowired
    private UserServicesImpl userServices;

    @Test
                                                                                                                                                                                                                                                                                                                                                                                    @Sql(scripts = {"/db/data.sql"})
    public void testFindUserById(){
        User user = userServices.findUserBy(200L);
        assertThat(user).isNotNull();
    }


}