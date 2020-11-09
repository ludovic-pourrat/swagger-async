package io.swagger.v2.async.oas.models.composition;

import io.swagger.v2.aas.annotations.media.Schema;

public interface Pet extends Animal {
    @Schema(required = true, description = "The pet type")
    String getType();

    void setType(String type);

    @Schema(required = true, description = "The name of the pet")
    String getName();

    void setName(String name);

    @Schema(required = true)
    Boolean getIsDomestic();

    void setIsDomestic(Boolean isDomestic);
}
