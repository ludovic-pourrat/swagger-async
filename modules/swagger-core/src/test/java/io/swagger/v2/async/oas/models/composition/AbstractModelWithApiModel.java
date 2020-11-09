package io.swagger.v2.async.oas.models.composition;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import io.swagger.v2.aas.annotations.media.Schema;

@Schema(name = "MyProperty")
@JsonSubTypes({@JsonSubTypes.Type(value = ModelWithUrlProperty.class), @JsonSubTypes.Type(value = ModelWithValueProperty.class)})
public abstract class AbstractModelWithApiModel {

    private final String type;

    protected AbstractModelWithApiModel(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
