package com.ats.todolist.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerEndpointGroupings {

    @Bean
    GroupedOpenApi allEndpoints(){
        return GroupedOpenApi.builder()
                .group("All Endpoints")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    GroupedOpenApi authEndpoints(){
        return GroupedOpenApi.builder()
                .group("Authentication Endpoints")
                .pathsToMatch("/api/v1/auth/**")
                .build();
    }

    @Bean
    GroupedOpenApi adminEndpoints(){
        return GroupedOpenApi.builder()
                .group("Admin Endpoints")
                .pathsToMatch("/api/v1/todos/complete/{id}")
                .build();
    }

    @Bean
    GroupedOpenApi userEndpoints(){
        return GroupedOpenApi.builder()
                .group("User Endpoints")
                .pathsToMatch("/api/v1/todos/**")
                .build();
    }
}
