package io.swagger.v2.aas.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v2.async.util.ObjectMapperFactory;

public class IntegrationObjectMapperFactory extends ObjectMapperFactory {

    public static ObjectMapper createJson() {
        return ObjectMapperFactory.createJson();
    }
}
