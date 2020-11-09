package io.swagger.v2.async.oas.models;

import io.swagger.v2.aas.annotations.media.Schema;

public class ReadOnlyFields {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    public Long id;
}
