package io.swagger.v2.async.oas.models;

import io.swagger.v2.aas.annotations.media.Schema;

@Schema(name = "sample_model")
public class ModelWithAltPropertyName {
    protected int id;

    @Schema(
            name = "the_id",
            description = "Note, this is server generated.",
            title = "Read-only")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}