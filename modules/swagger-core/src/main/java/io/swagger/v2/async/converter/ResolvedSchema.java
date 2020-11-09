package io.swagger.v2.async.converter;

import io.swagger.v2.aas.models.media.Schema;

import java.util.Map;

public class ResolvedSchema {
    public Schema schema;
    public Map<String, Schema> referencedSchemas;
}
