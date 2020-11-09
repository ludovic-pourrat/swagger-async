package io.swagger.v2.aas.integration;

import io.swagger.v2.aas.integration.api.AsyncAPIConfiguration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileAsyncApiConfigurationLoader implements StringAsyncApiConfigurationLoader {

    @Override
    public AsyncAPIConfiguration load(String path) throws IOException {
        File file = new File(path);
        return deserializeConfig(path, readInputStreamToString(new FileInputStream(file)));
    }

    @Override
    public boolean exists(String path) {
        File file = new File(path);
        return file.exists();
    }
}
