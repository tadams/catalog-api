package com.example.api.catalog.config;

import java.util.Optional;
import java.util.Properties;
import java.util.function.BiFunction;
import java.util.function.Supplier;

public class Config extends Properties {
    static class ConfigException extends RuntimeException {
        ConfigException(String message) {
            super(message);
        }
    }

    public String getProperty(final String key) {
        String property = super.getProperty(key);
        if (property == null) {
            throw new ConfigException(String.format("Missing value for key '%s'!", key));
        }
        return property;
    }

    BiFunction<Boolean, Supplier<String>, Optional<String>> compose =
            (flag, valSupplier) -> flag ? Optional.of(valSupplier.get()) : Optional.empty();

    public Optional<String> getConfigString(String key) {
        return compose.apply(
                this.containsKey(key), () -> this.getProperty(key)
                            );
    }
}
