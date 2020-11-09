package io.swagger.v2.aas.integration;

import io.swagger.v2.aas.integration.api.AsyncAPIConfiguration;
import io.swagger.v2.aas.integration.api.AsyncApiConfigurationLoader;

import java.io.IOException;

// TODO
public class URLAsyncApiConfigurationLoader implements AsyncApiConfigurationLoader {

    @Override
    public AsyncAPIConfiguration load(String path) throws IOException {
        return null;
    }

    @Override
    public boolean exists(String path) {
        return false;
    }
}
