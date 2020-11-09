package io.swagger.v2.async.oas.models;

import io.swagger.v2.aas.annotations.media.ArraySchema;
import io.swagger.v2.aas.annotations.media.Schema;

public class ModelWithModelPropertyOverrides {
    @ArraySchema(schema = @Schema(implementation = Children.class))
    private String children;

    public String getChildren() {
        return children;
    }

    public void setChildren(String children) {
        this.children = children;
    }
}
