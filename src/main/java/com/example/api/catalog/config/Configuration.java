package com.example.api.catalog.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.web.servlet.function.RequestPredicates.GET;
import static org.springframework.web.servlet.function.RouterFunctions.route;

@org.springframework.context.annotation.Configuration
@EnableConfigurationProperties
public class Configuration {

    @Bean
    RouterFunction<ServerResponse> routes(PolarProperties polarProperties) {
        return route(GET("/"), request -> ServerResponse.ok().body(polarProperties.getGreeting()));
    }
}
