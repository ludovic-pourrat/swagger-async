package io.swagger.v2.async.oas.models.composition;

import io.swagger.v2.aas.annotations.media.Schema;

public class PetWithSchemaSubtypes extends AnimalWithSchemaSubtypes {
    private String name;
    private String type;
    private Boolean isDomestic;

    @Schema(required = true, description = "The pet type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Schema(required = true, description = "The name of the pet")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Schema(required = true)
    public Boolean getIsDomestic() {
        return isDomestic;
    }

    public void setIsDomestic(Boolean isDomestic) {
        this.isDomestic = isDomestic;
    }
}
