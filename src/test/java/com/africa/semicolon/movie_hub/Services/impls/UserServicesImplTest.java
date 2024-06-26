package com.africa.semicolon.movie_hub.Services.impls;

import com.africa.semicolon.movie_hub.dto.UserRequest;
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
        User user = userServices.getUserBy(200L);
        assertThat(user).isNotNull();
    }


    @Test

    public void testRegisterUser() {
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail("user@Test.com");
        userRequest.setUserPassword("1234");
        var response = userServices.addUser(userRequest);
        assertThat(response.getUserPassword()).isNotEqualTo(userRequest.getUserPassword());
    }
}