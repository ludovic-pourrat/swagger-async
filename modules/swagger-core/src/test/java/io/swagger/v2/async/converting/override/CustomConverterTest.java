package io.swagger.v2.async.converting.override;

import static org.testng.Assert.*;

import java.util.Iterator;

import com.fasterxml.jackson.databind.JavaType;
import io.swagger.v2.aas.models.media.Schema;
import io.swagger.v2.async.converter.AnnotatedType;
import io.swagger.v2.async.converter.ModelConverter;
import io.swagger.v2.async.converter.ModelConverterContext;
import io.swagger.v2.async.converter.ModelConverters;
import io.swagger.v2.async.util.Json;
import org.testng.annotations.Test;

public class CustomConverterTest {

    @Test(description = "it should ignore properties with type Bar")
    public void testCustomConverter() {
        // add the custom converter
        final ModelConverters converters = new ModelConverters();
        converters.addConverter(new CustomConverter());

        final Schema model = converters.read(Foo.class).get("Foo");
        assertNotNull(model);
        assertEquals(model.getProperties().size(), 1);

        final Schema barProperty = (Schema) model.getProperties().get("bar");
        assertNull(barProperty);

        final Schema titleProperty = (Schema) model.getProperties().get("title");
        assertNotNull(titleProperty);
    }

    class CustomConverter implements ModelConverter {

        @Override
        public Schema resolve(AnnotatedType type, ModelConverterContext context, Iterator<ModelConverter> chain) {
            final JavaType jType = Json.mapper().constructType(type.getType());
            if (jType != null) {
                final Class<?> cls = jType.getRawClass();
                if (cls.equals(Bar.class)) {
                    return null;
                } else {
                    return chain.next().resolve(type, context, chain);
                }
            } else {
                return null;
            }
        }
    }

    class Foo {
        public Bar bar = null;
        public String title = null;
    }

    class Bar {
        public String foo = null;
    }
}
