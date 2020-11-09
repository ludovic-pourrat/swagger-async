package io.swagger.v2.async.resolving.resources;

import io.swagger.v2.aas.annotations.media.Schema;

import java.util.HashSet;
import java.util.Set;

@Schema(description = "Thing")
public class MyThing {
    private final Set<MyThing> otherThings;

    public MyThing() {
        otherThings = new HashSet<>();
    }

    @Schema(description = "Other related things")
    public Set<MyThing> getOtherThings() {
        return otherThings;
    }
}

