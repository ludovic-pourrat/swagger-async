package io.swagger.v2.async.oas.models.composition;

import io.swagger.v2.aas.annotations.media.Schema;

@Schema(description = "and Thing2", allOf = {AbstractBaseModelWithSubTypes.class})
public class Thing2 extends AbstractBaseModelWithSubTypes {

    @Schema(description = "Override the abstract a")
    public String a;
    @Schema(description = "Thing2 has an additional field")
    public String s;
}
