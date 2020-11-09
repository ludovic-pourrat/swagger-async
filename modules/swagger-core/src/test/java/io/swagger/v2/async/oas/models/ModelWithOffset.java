package io.swagger.v2.async.oas.models;

import io.swagger.v2.aas.annotations.media.Schema;

public class ModelWithOffset {
    public String id;

    @Schema(implementation = java.time.OffsetDateTime.class)
    public String offset;
}
