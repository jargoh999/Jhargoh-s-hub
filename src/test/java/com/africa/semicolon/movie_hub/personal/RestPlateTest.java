package com.africa.semicolon.movie_hub.personal;

import com.africa.semicolon.movie_hub.MovieHubApplication;
import com.africa.semicolon.movie_hub.dto.MediaResponse;
import com.africa.semicolon.movie_hub.dto.UserRequest;
import com.africa.semicolon.movie_hub.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = MovieHubApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class RestPlateTest {
  @LocalServerPort
    private int port;
@Autowired
  private RestTemplate restTemplate;


   @Test
    public void addUserTest(){
       UserRequest employee = new UserRequest(100L,"1234","111111", LocalDateTime.now(),LocalDateTime.now());
       ResponseEntity<?> responseEntity = restTemplate
               .postForEntity("http://localhost:" + port + "/api/v1/media", employee, MediaResponse.class);
       assertEquals(201, responseEntity.getStatusCodeValue());
   }


   @Test
   public void testGetMedia(){

       assertNotNull(restTemplate
               .getForObject("http://localhost:" + port + `/api/v1/media/{100L}`, ResponseEntity.class));
   }





}
