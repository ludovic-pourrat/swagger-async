package io.swagger.v2.async.jackson.mixin;

import com.fasterxml.jackson.annotation.JsonFormat;

public abstract class DateSchemaMixin {

    @JsonFormat (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public abstract Object getExample();
}
