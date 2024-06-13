package com.boardgame.boardGameHelper.config;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.StreamReadConstraints;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class jacksonconfig {
    @Bean
    Jackson2ObjectMapperBuilderCustomizer customStreamReadConstraints() {
        return (builder) -> builder.postConfigurer((objectMapper) -> objectMapper.getFactory()
                .setStreamReadConstraints(StreamReadConstraints.builder().maxNestingDepth(2000).maxStringLength(100_000_000).build()));
    }
}
