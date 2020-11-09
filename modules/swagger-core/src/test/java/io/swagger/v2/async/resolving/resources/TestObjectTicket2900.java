package io.swagger.v2.async.resolving.resources;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v2.aas.annotations.media.Schema;

    @Schema(name = "SomeDTO")
    public class TestObjectTicket2900 {

        @Schema(implementation = MyJsonPrimitive.class)
        public GsonJsonPrimitive value;

        @Schema(
                description = "Description of value",
                oneOf = { String.class, Number.class }
        )
        public GsonJsonPrimitive valueWithMixIn;


        public class GsonJsonPrimitive {
            private String foo;
            public String getFoo(){return foo;}
        }

        @Schema(
                description = "Description of value",
                oneOf = { String.class, Number.class }
        )
        public class MyJsonPrimitive {
        }

        public abstract class GsonJsonPrimitiveMixIn {
            @JsonIgnore
            public abstract String getFoo();
        }

    }