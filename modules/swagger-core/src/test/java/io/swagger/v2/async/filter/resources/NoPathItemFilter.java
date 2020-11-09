package io.swagger.v2.async.filter.resources;

import io.swagger.v2.async.filter.AbstractSpecFilter;
import io.swagger.v2.async.model.ApiDescription;
import io.swagger.v2.aas.models.PathItem;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Does nothing
 **/
public class NoPathItemFilter extends AbstractSpecFilter {
    @Override
    public Optional<PathItem> filterPathItem(PathItem pathItem, ApiDescription api, Map<String, List<String>> params, Map<String, String> cookies, Map<String, List<String>> headers) {
        if (StringUtils.isBlank(pathItem.get$ref())) {
            return Optional.empty();
        }
        return Optional.of(pathItem);
    }
}