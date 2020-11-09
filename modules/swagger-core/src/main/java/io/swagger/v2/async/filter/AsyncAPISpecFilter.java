package io.swagger.v2.async.filter;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import io.swagger.v2.aas.models.AsyncAPI;
import io.swagger.v2.aas.models.Operation;
import io.swagger.v2.aas.models.PathItem;
import io.swagger.v2.aas.models.media.Schema;
import io.swagger.v2.aas.models.parameters.Parameter;
import io.swagger.v2.aas.models.parameters.RequestBody;
import io.swagger.v2.aas.models.responses.ApiResponse;
import io.swagger.v2.async.model.ApiDescription;

public interface AsyncAPISpecFilter {

    Optional<AsyncAPI> filterAsyncAPI(
            AsyncAPI asyncAPI,
            Map<String, List<String>> params,
            Map<String, String> cookies,
            Map<String, List<String>> headers);

    Optional<PathItem> filterPathItem(
            PathItem pathItem,
            ApiDescription api,
            Map<String, List<String>> params,
            Map<String, String> cookies,
            Map<String, List<String>> headers);

    Optional<Operation> filterOperation(
            Operation operation,
            ApiDescription api,
            Map<String, List<String>> params,
            Map<String, String> cookies,
            Map<String, List<String>> headers);

    Optional<Parameter> filterParameter(
            Parameter parameter,
            Operation operation,
            ApiDescription api,
            Map<String, List<String>> params,
            Map<String, String> cookies,
            Map<String, List<String>> headers);

    Optional<RequestBody> filterRequestBody(
            RequestBody requestBody,
            Operation operation,
            ApiDescription api,
            Map<String, List<String>> params,
            Map<String, String> cookies,
            Map<String, List<String>> headers);

    Optional<ApiResponse> filterResponse(
            ApiResponse response,
            Operation operation,
            ApiDescription api,
            Map<String, List<String>> params,
            Map<String, String> cookies,
            Map<String, List<String>> headers);

    Optional<Schema> filterSchema(
            Schema schema,
            Map<String, List<String>> params,
            Map<String, String> cookies,
            Map<String, List<String>> headers);

    Optional<Schema> filterSchemaProperty(
            Schema property,
            Schema schema,
            String propName,
            Map<String, List<String>> params,
            Map<String, String> cookies,
            Map<String, List<String>> headers);

    boolean isRemovingUnreferencedDefinitions();
}