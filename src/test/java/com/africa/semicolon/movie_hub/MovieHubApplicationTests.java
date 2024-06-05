package com.africa.semicolon.movie_hub;

import com.africa.semicolon.movie_hub.Repository.MediaRepo;
import com.africa.semicolon.movie_hub.Services.impls.MediaAndUserImpl;
import com.africa.semicolon.movie_hub.dto.MediaRequest;
import com.africa.semicolon.movie_hub.dto.MediaResponse;
import com.africa.semicolon.movie_hub.model.Category;
import com.africa.semicolon.movie_hub.model.Media;
import com.fasterxml.jackson.databind.node.TextNode;
import com.github.fge.jackson.jsonpointer.JsonPointer;
import com.github.fge.jackson.jsonpointer.JsonPointerException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.JsonPatchOperation;
import com.github.fge.jsonpatch.ReplaceOperation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.multipart.MultipartFile;
import org.xmlunit.builder.Input;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static com.africa.semicolon.movie_hub.model.Category.ACTION;
import static com.africa.semicolon.movie_hub.model.Category.MOVIE;
import static com.africa.semicolon.movie_hub.utils.TestUtils.*;
import static org.assertj.core.api.Assertions.assertThat;
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
        var mediaRequest = buildUpMediaRequest(streamOfData);
		mediaResponse=mediaAndUserImpl.uploadVideo(mediaRequest);
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
	@Test
	@Sql(scripts = {"/db/data.sql"})
	public void updateMediaTest() throws JsonPointerException, JsonPatchException {
		Category category = mediaAndUserImpl.getMediaById(103L).getCategory();
		assertThat(category).isNotEqualTo(MOVIE);
		List<JsonPatchOperation> operations = List.of(
				new ReplaceOperation(new JsonPointer("/category"),
				  new TextNode(ACTION.name())));
          JsonPatch updateMediaRequest = new JsonPatch(operations);
		  MediaResponse response = mediaAndUserImpl.updateMedia(103L,updateMediaRequest);
		  assertThat(response).isNotNull();
		  category=mediaAndUserImpl.getMediaById(103L).getCategory();
		  assertThat(category).isEqualTo(ACTION);
	}





}
