package com.africa.semicolon.movie_hub.utils;

import com.africa.semicolon.movie_hub.dto.MediaRequest;
import com.africa.semicolon.movie_hub.dto.MediaResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

import static com.africa.semicolon.movie_hub.model.Category.ACTION;


public class TestUtils {
public static final String TEST_IMAGE_PATH = "C:\\Users\\DELL\\Pictures\\Screenshots\\Screenshot 2023-11-18 123211.png";
public static final String TEST_VIDEO_PATH = "C:\\Users\\DELL\\Downloads\\vid.mp4";

    public static MediaRequest buildUpMediaRequest(InputStream inputStream) throws IOException {
        MultipartFile file = new MockMultipartFile("screenShot",inputStream);
        MediaRequest mediaRequest = new MediaRequest();
        mediaRequest.setFile(file);
        mediaRequest.setUserId(200L);
        mediaRequest.setDescription("just a video");
        mediaRequest.setCategory(ACTION);
        return  mediaRequest;
    }


}
