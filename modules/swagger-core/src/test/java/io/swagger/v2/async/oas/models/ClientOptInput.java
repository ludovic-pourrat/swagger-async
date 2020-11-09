package io.swagger.v2.async.oas.models;

import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.v2.aas.annotations.media.Schema;
import io.swagger.v2.aas.models.AsyncAPI;

public class ClientOptInput {
    private String opts;
    private JsonNode model;
    private AsyncAPI swagger;

    public String getOpts() {
        return opts;
    }

    public void setOpts(String opts) {
        this.opts = opts;
    }

    @Schema(hidden = true)
    public JsonNode getModel() {
        return model;
    }

    public void setModel(JsonNode model) {
        this.model = model;
    }

    @Schema(type = "Object")
    public AsyncAPI getSwagger() {
        return swagger;
    }

    public void setSwagger(AsyncAPI swagger) {
        this.swagger = swagger;
    }
}