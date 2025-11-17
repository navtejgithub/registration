package com.registration.registration.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        // You can add configuration here, like setting the matching strategy
        ModelMapper modelMapper = new ModelMapper();

        // Example configuration (optional, but often recommended)
        // modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        return modelMapper;
    }
}
