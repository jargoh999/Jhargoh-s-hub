package com.africa.semicolon.movie_hub.ControllerTest;

import com.africa.semicolon.movie_hub.dto.MediaRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplicationExtensionsKt;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockPart;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.africa.semicolon.movie_hub.utils.TestUtils.TEST_VIDEO_PATH;
import static com.africa.semicolon.movie_hub.utils.TestUtils.buildUpMediaRequest;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TestControllers {

   @Autowired
   private MockMvc mockMvc;
    @Test
    @Sql(scripts = {"/db/data.sql"})
    public void testMediaController(){

        ObjectMapper mapper = new ObjectMapper();
        try(InputStream inputStream = Files.newInputStream(Path.of(TEST_VIDEO_PATH)  )){
            MultipartFile file = new MockMultipartFile("test media file ",inputStream);
            mockMvc.perform(multipart("/api/v1/media")
                            .file(file.getName(),file.getBytes())
                            .part(new MockPart("mediaId", "200".getBytes()))
                            .part( new MockPart("description", "test description".getBytes()))
                            .part(new MockPart("category", "ACTION".getBytes()))
                            .contentType(MediaType.MULTIPART_FORM_DATA))
                            .andExpect(status().isCreated())
                            .andDo(print());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
