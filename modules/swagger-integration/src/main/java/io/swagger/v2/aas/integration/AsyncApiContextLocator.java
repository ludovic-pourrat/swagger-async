package io.swagger.v2.aas.integration;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import io.swagger.v2.aas.integration.api.AsyncApiContext;

public class AsyncApiContextLocator {

    private static AsyncApiContextLocator instance;

    private ConcurrentMap<String, AsyncApiContext> map = new ConcurrentHashMap<>();

    private AsyncApiContextLocator() {
    }

    public static synchronized AsyncApiContextLocator getInstance() {
        if (instance == null) {
            instance = new AsyncApiContextLocator();
        }
        return instance;
    }

    public AsyncApiContext getOpenApiContext(String id) {
        return map.get(id);
    }

    public void putOpenApiContext(String id, AsyncApiContext asyncApiContext) {
        map.put(id, asyncApiContext);
    }
}
