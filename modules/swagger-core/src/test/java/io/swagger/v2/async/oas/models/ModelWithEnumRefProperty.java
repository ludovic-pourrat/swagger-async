package io.swagger.v2.async.oas.models;

import io.swagger.v2.aas.annotations.media.Schema;

public class ModelWithEnumRefProperty {
    private TestEnum a;
    private TestEnum b;

    @Schema(enumAsRef = true)
    public TestSecondEnum c;

    public TestSecondEnum d;

    @Schema(enumAsRef = true)
    public TestEnum getA() {
        return a;
    }

    public void setA(TestEnum e) {
        this.a = a;
    }

    @Schema(enumAsRef = true)
    public TestEnum getB() {
        return b;
    }

    public void setB(TestEnum b) {
        this.b = b;
    }
}
