package io.swagger.v2.async.oas.models.composition;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.v2.aas.annotations.media.Schema;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXTERNAL_PROPERTY,
        property = "type")
@Schema(subTypes = {HumanWithSchemaSubtypes.class, PetWithSchemaSubtypes.class})
public class AnimalWithSchemaSubtypes {

    String type;
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
