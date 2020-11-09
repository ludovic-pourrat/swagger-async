package io.swagger.v2.aas.integration;

import io.swagger.v2.aas.integration.api.AsyncAPIConfigBuilder;
import io.swagger.v2.aas.integration.api.AsyncAPIConfiguration;
import io.swagger.v2.aas.integration.api.AsyncApiConfigurationLoader;

import java.io.IOException;
import java.util.ServiceLoader;

// doesn't support multiple configs
public class ServiceAsyncApiConfigurationLoader implements AsyncApiConfigurationLoader {

    @Override
    public AsyncAPIConfiguration load(String path) throws IOException {

        ServiceLoader<AsyncAPIConfigBuilder> loader = ServiceLoader.load(AsyncAPIConfigBuilder.class);
        if (loader.iterator().hasNext()) {
            return loader.iterator().next().build();
        }
        throw new IOException("Error loading OpenAPIConfigBuilder service implementation.");
    }

    @Override
    public boolean exists(String path) {

        try {
            ServiceLoader<AsyncAPIConfigBuilder> loader = ServiceLoader.load(AsyncAPIConfigBuilder.class);
            if (loader.iterator().hasNext()) {
                loader.iterator().next();
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}
