package com.africa.semicolon.movie_hub;

import com.africa.semicolon.movie_hub.Repository.MediaRepo;
import com.africa.semicolon.movie_hub.Services.impls.MediaAndUserImpl;
import com.africa.semicolon.movie_hub.dto.MediaRequest;
import com.africa.semicolon.movie_hub.dto.MediaResponse;
import com.africa.semicolon.movie_hub.model.Media;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


@SpringBootTest
class MovieHubApplicationTests {

	@Autowired
	private MediaRepo mediaRepo;
	@Autowired
	private MediaAndUserImpl mediaAndUserImpl;

	MediaRequest mediaRequest;
	MediaResponse mediaResponse;
	Media media;

	Path path;

	Media mappedMedia;




	@Test
	public void uploadMedia_MapsRequestToMediaObject() throws IOException {
		mediaRequest = new MediaRequest();
		mediaResponse = new MediaResponse();
		media = new Media();
		mappedMedia = new Media();
		Path path1 = Paths.get("C:\\Users\\DELL\\Pictures\\Screenshots\\Screenshot 2023-11-18 123211.png");
		var streamOfData = Files.newInputStream(path1);
		MultipartFile file = new MockMultipartFile("screenShot",streamOfData);
		mediaRequest.setFile(file);
		mediaRequest.setDescription("just a picture");
		mediaResponse = mediaAndUserImpl.uploadMedia(mediaRequest);
		assertEquals(mediaRequest.getDescription(), mediaResponse.getDescription());
		assertNotNull(mediaResponse.getUrl());
		assertEquals("successfully saved", mediaResponse.getMessage());
	}

}
