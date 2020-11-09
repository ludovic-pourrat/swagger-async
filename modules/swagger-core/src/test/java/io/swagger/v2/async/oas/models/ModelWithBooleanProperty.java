package io.swagger.v2.async.oas.models;

import io.swagger.v2.aas.annotations.media.Schema;

public class ModelWithBooleanProperty {
    @Schema(allowableValues = "true")
    public Boolean isGreat;

    @Schema(allowableValues = {"1", "2"})
    public Integer intValue;
}
