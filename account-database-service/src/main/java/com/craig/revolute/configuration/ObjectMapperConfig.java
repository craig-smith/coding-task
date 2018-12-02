package com.craig.revolute.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.kotlin.KotlinModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectMapperConfig {

    @Bean
    public ObjectMapper configure() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new KotlinModule());
        return mapper;
    }
}
