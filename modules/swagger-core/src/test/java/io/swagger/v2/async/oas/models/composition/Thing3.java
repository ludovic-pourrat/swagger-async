package io.swagger.v2.async.oas.models.composition;

import io.swagger.v2.aas.annotations.media.Schema;

@Schema(description = "Thing3", allOf = {AbstractBaseModelWithoutFields.class})
public class Thing3 extends AbstractBaseModelWithoutFields {

    @Schema(description = "Additional field a")
    String a;
    @Schema(description = "Additional field x")
    int x;

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
}
