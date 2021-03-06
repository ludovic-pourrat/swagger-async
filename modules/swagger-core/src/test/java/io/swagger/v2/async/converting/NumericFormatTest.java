package io.swagger.v2.async.converting;

import static io.swagger.v2.async.util.TestUtils.*;
import static org.testng.Assert.*;

import java.math.BigDecimal;
import java.util.Map;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;

import io.swagger.v2.async.converter.ModelConverters;
import io.swagger.v2.async.util.Json;
import io.swagger.v2.aas.models.media.Schema;
import org.testng.annotations.Test;

public class NumericFormatTest {
    @Test
    public void testFormatOfInteger() {
        final Map<String, Schema> models = ModelConverters.getInstance().readAll(ModelWithIntegerFields.class);
        assertEquals(models.size(), 1);

        String json = Json.pretty(models);
        assertEquals(normalizeLineEnds(json),
                "{\n" +
                        "  \"ModelWithIntegerFields\" : {\n" +
                        "    \"type\" : \"object\",\n" +
                        "    \"properties\" : {\n" +
                        "      \"id\" : {\n" +
                        "        \"minimum\" : 3,\n" +
                        "        \"type\" : \"integer\",\n" +
                        "        \"format\" : \"int32\"\n" +
                        "      }\n" +
                        "    }\n" +
                        "  }\n" +
                        "}");
    }

    @Test
    public void testFormatOfDecimal() {
        final Map<String, Schema> models = ModelConverters.getInstance().readAll(ModelWithDecimalFields.class);
        assertEquals(models.size(), 1);

        String json = Json.pretty(models);
        assertEquals(normalizeLineEnds(json),
                "{\n" +
                        "  \"ModelWithDecimalFields\" : {\n" +
                        "    \"type\" : \"object\",\n" +
                        "    \"properties\" : {\n" +
                        "      \"id\" : {\n" +
                        "        \"minimum\" : 3.3,\n" +
                        "        \"exclusiveMinimum\" : false,\n" +
                        "        \"type\" : \"number\",\n" +
                        "        \"format\" : \"double\"\n" +
                        "      }\n" +
                        "    }\n" +
                        "  }\n" +
                        "}");
    }

    @Test
    public void testFormatOfBigDecimal() {
        final Map<String, Schema> models = ModelConverters.getInstance().readAll(ModelWithoutScientificFields.class);
        assertEquals(models.size(), 1);

        String json = Json.pretty(models);

        assertEquals(normalizeLineEnds(json),
                "{\n" +
                        "  \"ModelWithoutScientificFields\" : {\n" +
                        "    \"type\" : \"object\",\n" +
                        "    \"properties\" : {\n" +
                        "      \"id\" : {\n" +
                        "        \"maximum\" : 9999999999999999.99,\n" +
                        "        \"exclusiveMaximum\" : false,\n" +
                        "        \"minimum\" : -9999999999999999.99,\n" +
                        "        \"exclusiveMinimum\" : false,\n" +
                        "        \"type\" : \"number\"\n" +
                        "      }\n" +
                        "    }\n" +
                        "  }\n" +
                        "}");

    }

    static class ModelWithIntegerFields {
        @io.swagger.v2.aas.annotations.media.Schema
        @Min(value = 3)
        public Integer id;
    }

    static class ModelWithDecimalFields {
        @io.swagger.v2.aas.annotations.media.Schema
        @DecimalMin(value = "3.3")
        public Double id;
    }

    static class ModelWithoutScientificFields {
        @io.swagger.v2.aas.annotations.media.Schema
        @DecimalMin(value = "-9999999999999999.99")
        @DecimalMax(value = "9999999999999999.99")
        public BigDecimal id;
    }
}
