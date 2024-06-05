package com.africa.semicolon.movie_hub.Services;

import com.africa.semicolon.movie_hub.dto.MediaRequest;
import com.africa.semicolon.movie_hub.dto.MediaResponse;
import com.africa.semicolon.movie_hub.model.Category;
import com.africa.semicolon.movie_hub.model.Media;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

import java.util.List;

public interface MediaServices {
    MediaResponse uploadMedia (MediaRequest mediaRequest);
    MediaResponse uploadVideo(MediaRequest uploadRequest);

    Media getMediaById(Long id);

    List<Media> getUserByCategoryId(Category category);

    MediaResponse updateMedia (Long mediaId, JsonPatch updateMediaRequest) throws JsonPatchException;

}
