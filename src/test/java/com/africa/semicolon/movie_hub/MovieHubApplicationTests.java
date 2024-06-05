package com.africa.semicolon.movie_hub;

import com.africa.semicolon.movie_hub.Repository.MediaRepo;
import com.africa.semicolon.movie_hub.Services.impls.MediaAndUserImpl;
import com.africa.semicolon.movie_hub.dto.MediaRequest;
import com.africa.semicolon.movie_hub.dto.MediaResponse;
import com.africa.semicolon.movie_hub.model.Media;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static com.africa.semicolon.movie_hub.model.Category.ACTION;
import static com.africa.semicolon.movie_hub.utils.TestUtils.TEST_IMAGE_PATH;
import static com.africa.semicolon.movie_hub.utils.TestUtils.TEST_VIDEO_PATH;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MovieHubApplicationTests {

	@Autowired
	private MediaRepo mediaRepo;
	@Autowired
	private MediaAndUserImpl mediaAndUserImpl;

	MediaRequest mediaRequest;
	MediaResponse mediaResponse;
	Media media;
	Media mappedMedia;




	@Test
	@Sql(scripts = {"/db/data.sql"})
	public void uploadMedia_MapsRequestToMediaObject() throws IOException {
		mediaRequest = new MediaRequest();
		mediaResponse = new MediaResponse();
		media = new Media();
		mappedMedia = new Media();
		Path path1 = Paths.get(TEST_IMAGE_PATH);
		var streamOfData = Files.newInputStream(path1);
		MultipartFile file = new MockMultipartFile("screenShot",streamOfData);
		mediaRequest.setFile(file);
		mediaRequest.setUserId(201L);
		mediaRequest.setDescription("just a picture");
		mediaResponse = mediaAndUserImpl.uploadMedia(mediaRequest);
		assertEquals(mediaRequest.getDescription(), mediaResponse.getDescription());
		assertNotNull(mediaResponse.getUrl());
	}


	@Test
	@Sql(scripts = {"/db/data.sql"})
	public void uploadVideo_MapsRequestToMediaObject() throws IOException {
		mediaRequest = new MediaRequest();
		mediaResponse = new MediaResponse();
		media = new Media();
		mappedMedia = new Media();
		Path path1 = Paths.get( TEST_VIDEO_PATH );
		var streamOfData = Files.newInputStream(path1);
		MultipartFile file = new MockMultipartFile("screenShot",streamOfData);
		mediaRequest.setFile(file);
		mediaRequest.setUserId(200L);
		mediaRequest.setDescription("just a video");
		mediaRequest.setCategory(ACTION);
		mediaResponse = mediaAndUserImpl.uploadVideo(mediaRequest);
		assertEquals(mediaRequest.getDescription(), mediaResponse.getDescription());
		assertNotNull(mediaResponse.getUrl());
		assertNotNull(mediaResponse.getUser());
		assertSame(mediaResponse.getCategory(),ACTION);
	}


	@Test
    @Sql(scripts = {"/db/data.sql"})
    public void getMedia_ReturnsMediaObject() {
		Media media = mediaRepo.findMediaById(100L).get();
		assertNotNull(media, "Media not found");

	}

	@Test
	@Sql(scripts = {"/db/data.sql"})
	public void getMediaByCategory_ReturnsListMediaObject() {
		var media = mediaRepo.findMediaByCategory(ACTION);
		assertTrue(media.size()>2);
	}






}
