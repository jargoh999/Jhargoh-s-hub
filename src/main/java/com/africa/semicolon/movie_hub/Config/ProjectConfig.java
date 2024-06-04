package com.africa.semicolon.movie_hub.Config;

import com.africa.semicolon.movie_hub.utils.MediaUtils;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectConfig {

    @Bean
    public ModelMapper mapper(){
        return new ModelMapper();
    }

    @Bean
    public Cloudinary cloudinary(){
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", System.getenv("API_CLOUDINARY__CLOUDNAME"),
                "api_key", System.getenv("API_CLOUDINARY_KEY"),
                "api_secret", System.getenv("API_CLOUDINARY_SECRET_KEY")));
    }
}
