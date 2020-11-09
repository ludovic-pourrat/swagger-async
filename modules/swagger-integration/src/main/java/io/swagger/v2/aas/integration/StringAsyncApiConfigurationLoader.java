package io.swagger.v2.aas.integration;

import io.swagger.v2.aas.integration.api.AsyncAPIConfiguration;
import io.swagger.v2.aas.integration.api.AsyncApiConfigurationLoader;
import io.swagger.v2.async.util.Json;
import io.swagger.v2.async.util.Yaml;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface StringAsyncApiConfigurationLoader extends AsyncApiConfigurationLoader {

    Logger LOGGER = LoggerFactory.getLogger(StringAsyncApiConfigurationLoader.class);

    default AsyncAPIConfiguration deserializeConfig(String path, String configAsString) {

        try {
            if (path.toLowerCase().endsWith("json")) {
                return Json.mapper().readValue(configAsString, SwaggerConfiguration.class);
            } else { // assume yaml
                return Yaml.mapper().readValue(configAsString, SwaggerConfiguration.class);
            }

        } catch (Exception e) {
            LOGGER.error("exception reading config: " + e.getMessage(), e);
            return null;
        }

    }

}
