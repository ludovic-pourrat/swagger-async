package io.swagger.v2.aas.annotations.enums;

public enum ParameterIn {
    DEFAULT(""),
    HEADER("header"),
    QUERY("query"),
    PATH("path"),
    COOKIE("cookie");

    private String value;

    ParameterIn(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}