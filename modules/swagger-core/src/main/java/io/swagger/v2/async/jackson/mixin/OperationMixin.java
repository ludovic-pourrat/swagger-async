package io.swagger.v2.async.jackson.mixin;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v2.async.jackson.ApiResponsesSerializer;
import io.swagger.v2.async.jackson.CallbackSerializer;
import io.swagger.v2.aas.models.callbacks.Callback;
import io.swagger.v2.aas.models.responses.ApiResponses;

public abstract class OperationMixin {

    @JsonAnyGetter
    public abstract Map<String, Object> getExtensions();

    @JsonAnySetter
    public abstract void addExtension(String name, Object value);

    @JsonSerialize(contentUsing = CallbackSerializer.class)
    public abstract Map<String, Callback> getCallbacks();

    @JsonSerialize(using = ApiResponsesSerializer.class)
    public abstract ApiResponses getResponses();

}
