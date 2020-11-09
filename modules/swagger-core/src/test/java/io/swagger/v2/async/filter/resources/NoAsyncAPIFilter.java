package io.swagger.v2.async.filter.resources;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import io.swagger.v2.aas.models.AsyncAPI;
import io.swagger.v2.async.filter.AbstractSpecFilter;

/**
 * Does nothing
 **/
public class NoAsyncAPIFilter extends AbstractSpecFilter {

    public static final String VERSION = "2.0.0";

    @Override
    public Optional<AsyncAPI> filterAsyncAPI(AsyncAPI asyncAPI, Map<String, List<String>> params, Map<String, String> cookies, Map<String, List<String>> headers) {
        if (VERSION.equals(asyncAPI.getAsyncapi())) {
            return Optional.empty();
        }
        return Optional.of(asyncAPI);

    }
}
