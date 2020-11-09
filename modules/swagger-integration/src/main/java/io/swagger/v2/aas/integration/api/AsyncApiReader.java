package io.swagger.v2.aas.integration.api;

import java.util.Map;
import java.util.Set;

import io.swagger.v2.aas.models.AsyncAPI;

public interface AsyncApiReader {

    void setConfiguration(AsyncAPIConfiguration asyncApiConfiguration);

    AsyncAPI read(Set<Class<?>> classes, Map<String, Object> resources);
}
