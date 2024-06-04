package com.africa.semicolon.movie_hub.Services.impls;

import aj.org.objectweb.asm.commons.AnnotationRemapper;
import com.africa.semicolon.movie_hub.Repository.MediaRepo;
import com.africa.semicolon.movie_hub.Services.MediaServices;
import com.africa.semicolon.movie_hub.dto.MediaRequest;
import com.africa.semicolon.movie_hub.dto.MediaResponse;
import com.africa.semicolon.movie_hub.model.Media;


import com.africa.semicolon.movie_hub.utils.MediaUtils;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@AllArgsConstructor
public class MediaAndUserImpl implements MediaServices {

    private final MediaRepo mediaRepo;

    private final ModelMapper mapper;

    private Cloudinary cloudinary;


    @Override
    public MediaResponse uploadMedia(MediaRequest uploadRequest) {
        try {
            Map<?,?> response = cloudinary.uploader().upload(uploadRequest.getFile().getBytes(), new HashMap());
            String url = response.get("url").toString();
            Media media = new Media();
            media.setUrl(url);
            media.setDescription(uploadRequest.getDescription());
            media = mediaRepo.save(media);
           MediaResponse uploadResponse=mapper.map(media, MediaResponse.class);
           uploadResponse.setMessage("successfully saved");
           return uploadResponse;
        } catch (IOException exception) {
            System.err.println(exception.getMessage());
            exception.printStackTrace();
        }
        return null;
        // Slf4j --> simple logging facade for java
    }



}

