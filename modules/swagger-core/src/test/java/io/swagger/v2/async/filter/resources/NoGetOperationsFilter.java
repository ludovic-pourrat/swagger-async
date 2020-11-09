package io.swagger.v2.async.filter.resources;

import io.swagger.v2.async.filter.AbstractSpecFilter;
import io.swagger.v2.async.model.ApiDescription;
import io.swagger.v2.aas.models.Operation;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Sample filter to avoid all get operations for the resource
 **/
public class NoGetOperationsFilter extends AbstractSpecFilter {
    private static final String GET = "GET";

    @Override
    public Optional<Operation> filterOperation(Operation operation, ApiDescription api, Map<String, List<String>> params, Map<String, String> cookies, Map<String, List<String>> headers) {
        if (GET.equals(api.getMethod())) {
            return Optional.empty();
        }
        return Optional.of(operation);
    }
}