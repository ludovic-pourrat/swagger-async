package io.swagger.v2.async.resolving.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v2.aas.annotations.media.Schema;

public class TestObject3697 {
    @JsonProperty("id")
    private final Long id;

    @Schema(hidden = true)
    @JsonProperty("hidden")
    private final String hidden;


    @JsonCreator
    public TestObject3697(@JsonProperty("id") Long id,
                          @Schema(hidden = true)
                                              @JsonProperty("hidden") String hidden) {
        this.id = id;
        this.hidden = hidden;
    }

    public Long getId() {
        return id;
    }

    public String getHidden() {
        return hidden;
    }
}
