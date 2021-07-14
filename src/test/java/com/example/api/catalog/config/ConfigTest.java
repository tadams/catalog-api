package com.example.api.catalog.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ConfigTest {

    private Config config = new Config();

    @BeforeEach
    public void given() {
        config.setProperty("key", "value");
    }

    @Test
    public void shouldFindProperty() {
        assertThat(config.getConfigString("key")).get().isEqualTo("value");
    }

    @Test
    public void shouldReturnEmptyWhenKeyNotFound() {
        assertThat(config.getConfigString("notFound").isPresent()).isEqualTo(false);
    }

}