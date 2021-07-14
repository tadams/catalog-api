package com.example.api.catalog.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "polar")
@Data
public class PolarProperties {
    private String greeting;
}
