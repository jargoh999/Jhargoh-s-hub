package com.africa.semicolon.movie_hub.controllers;

import com.africa.semicolon.movie_hub.Services.impls.MediaAndUserImpl;
import com.africa.semicolon.movie_hub.dto.MediaRequest;
import com.africa.semicolon.movie_hub.dto.MediaResponse;
import com.africa.semicolon.movie_hub.utils.MediaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@Controller
@RequestMapping("/api/v1/media")
public class MediaController {
    @Autowired
    private MediaAndUserImpl mediaAndUser;

    @PostMapping(consumes = {MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<MediaResponse> uploadMedia(@ModelAttribute MediaRequest mediaRequest){
          return ResponseEntity.status(CREATED)
                  .body(mediaAndUser.uploadVideo(mediaRequest));
    }

   @GetMapping
    public ResponseEntity<?> getMediaForUser(@RequestParam Long userId){
        try {
            return ResponseEntity.ok(mediaAndUser.getMediaForUser(userId));
        }catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), BAD_REQUEST);
        }

    }


}
