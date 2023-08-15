package com.anuradha.annexservice.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PostMapper {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
