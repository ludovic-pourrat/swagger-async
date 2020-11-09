package io.swagger.v2.async.oas.models.composition;

import io.swagger.v2.aas.annotations.media.Schema;

@Schema(description = "Shake hands with Thing1", allOf = {AbstractBaseModelWithSubTypes.class})
public class Thing1 extends AbstractBaseModelWithSubTypes {

    @Schema(description = "Override the abstract a")
    public String a;
    @Schema(description = "Thing1 has an additional field")
    public int x;
}
