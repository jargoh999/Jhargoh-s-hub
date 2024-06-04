package com.africa.semicolon.movie_hub.contollers;

import com.africa.semicolon.movie_hub.Services.impls.MediaAndUserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.FOUND;

@RestController
public class ServicesController {

@Autowired
private MediaAndUserImpl mediaAndUser;






}
