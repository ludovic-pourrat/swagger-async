package io.swagger.v2.aas.integration;

import java.io.IOException;

import io.swagger.v2.aas.integration.api.AsyncAPIConfiguration;

public class ClasspathAsyncApiConfigurationLoader implements StringAsyncApiConfigurationLoader {

    @Override
    public AsyncAPIConfiguration load(String path) throws IOException {
        String sanitized = (path.startsWith("/") ? path : "/" + path);
        String configString = readInputStreamToString(this.getClass().getResource(sanitized).openStream());
        return deserializeConfig(path, configString);

    }

    @Override
    public boolean exists(String path) {
        String sanitized = (path.startsWith("/") ? path : "/" + path);
        return this.getClass().getResource(sanitized) != null;
    }
}
