package io.swagger.v2.aas.annotations.enums;

public enum SecuritySchemeIn {
    DEFAULT(""),
    HEADER("header"),
    QUERY("query"),
    COOKIE("cookie");

    private String value;

    SecuritySchemeIn(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}