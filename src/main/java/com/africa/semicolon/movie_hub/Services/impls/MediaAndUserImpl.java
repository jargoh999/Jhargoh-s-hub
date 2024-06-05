package com.africa.semicolon.movie_hub.Services.impls;

import aj.org.objectweb.asm.commons.AnnotationRemapper;
import com.africa.semicolon.movie_hub.Repository.MediaRepo;
import com.africa.semicolon.movie_hub.Repository.Users;
import com.africa.semicolon.movie_hub.Services.MediaServices;
import com.africa.semicolon.movie_hub.dto.MediaRequest;
import com.africa.semicolon.movie_hub.dto.MediaResponse;
import com.africa.semicolon.movie_hub.model.Category;
import com.africa.semicolon.movie_hub.model.Media;


import com.africa.semicolon.movie_hub.model.User;
import com.africa.semicolon.movie_hub.utils.MediaUtils;
import com.cloudinary.Cloudinary;
import com.cloudinary.EagerTransformation;
import com.cloudinary.utils.ObjectUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.IOException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@AllArgsConstructor
public class MediaAndUserImpl implements MediaServices {

    private final MediaRepo mediaRepo;

    private final ModelMapper mapper;

    private Cloudinary cloudinary;
    private Users users;


    @Override
    public MediaResponse uploadMedia(MediaRequest uploadRequest) {
            User user =users.findByUserId(uploadRequest.getUserId()).get();
        try {
            Map<?,?> response = cloudinary.uploader().upload(uploadRequest.getFile().getBytes(), new HashMap());
            String url = response.get("url").toString();
            Media media=mapper.map(uploadRequest,Media.class);
            media.setUrl(url);
            media.setUser(user);
            media = mediaRepo.save(media);
            return mapper.map(media, MediaResponse.class);
        } catch (IOException exception) {
            exception.printStackTrace();
            throw new RuntimeException("something went wrong");
        }
        // Slf4j --> simple logging facade for java
    }





    public MediaResponse uploadVideo(MediaRequest uploadRequest) {
        User user =users.findByUserId(uploadRequest.getUserId()).get();
        try {
           Map<?,?> response = cloudinary.uploader().upload(uploadRequest.getFile().getBytes(),ObjectUtils.asMap(
           "resource_type","auto"));
            String url = response.get("url").toString();
            Media media = mapper.map(uploadRequest, Media.class);
            media.setUrl(url);
            media.setUser(user);
            media = mediaRepo.save(media);
            return mapper.map(media, MediaResponse.class);
        } catch (IOException exception) {
            exception.printStackTrace();
            throw new RuntimeException("Something went wrong during video upload.");
        }
    }

    @Override
    public Media getMediaById(Long id) {
        return mediaRepo.findMediaById(id).
                orElseThrow(()-> new RuntimeException("media not found"));
    }

    @Override
    public List<Media> getUserByCategoryId(Category category) {
        return mediaRepo.findMediaByCategory(category);}

    @Override
    public MediaResponse updateMedia(Long mediaId, MediaRequest mediaRequest) {

    }


}

