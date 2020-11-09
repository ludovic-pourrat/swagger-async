package io.swagger.v2.async.resolving;

import io.swagger.v2.async.converter.ModelConverters;
import io.swagger.v2.aas.models.media.DateSchema;
import io.swagger.v2.aas.models.media.DateTimeSchema;
import io.swagger.v2.aas.models.media.Schema;
import io.swagger.v2.aas.models.media.StringSchema;
import org.joda.time.LocalDate;
import org.testng.annotations.Test;

import java.time.Instant;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class JodaLocalDateConverterTest {

    @Test
    public void testJodaLocalDate() {
        final Map<String, Schema> models = ModelConverters.getInstance().read(ModelWithJodaLocalDate.class);
        assertEquals(models.size(), 1);

        final Schema model = models.get("ModelWithJodaLocalDate");

        final Schema dateTimeProperty = (Schema) model.getProperties().get("createdAt");
        assertTrue(dateTimeProperty instanceof DateSchema);
        assertTrue(model.getRequired().contains("createdAt"));
        assertEquals(dateTimeProperty.getDescription(), "creation localDate");

        final Schema nameProperty = (Schema) model.getProperties().get("name");
        assertTrue(nameProperty instanceof StringSchema);
        assertEquals(nameProperty.getDescription(), "name of the model");
    }

    class ModelWithJodaLocalDate {
        @io.swagger.v2.aas.annotations.media.Schema(description = "name of the model")
        public String name;

        @io.swagger.v2.aas.annotations.media.Schema(description = "creation localDate", required = true)
        public LocalDate createdAt;
    }

    @Test
    public void testJavaTimeInstant() {
        final Map<String, Schema> models = ModelConverters.getInstance().read(ModelWithJavaTimeInstant.class);
        assertEquals(models.size(), 1);
        final Schema model = models.get("ModelWithJavaTimeInstant");

        final Schema dateTimeProperty = (Schema) model.getProperties().get("createdAt");
        assertTrue(dateTimeProperty instanceof DateTimeSchema);
    }

    class ModelWithJavaTimeInstant {
        @io.swagger.v2.aas.annotations.media.Schema(description = "name of the model")
        public String name;
        public Instant createdAt;
    }
}
