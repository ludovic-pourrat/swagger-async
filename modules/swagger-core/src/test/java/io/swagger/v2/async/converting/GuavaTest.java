package io.swagger.v2.async.converting;

import io.swagger.v2.async.converter.ModelConverters;
import io.swagger.v2.async.matchers.SerializationMatchers;
import io.swagger.v2.async.oas.models.GuavaModel;
import io.swagger.v2.async.util.ResourceUtils;
import io.swagger.v2.aas.models.media.Schema;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;

public class GuavaTest {

    @Test(description = "convert a model with Guava optionals")
    public void convertModelWithGuavaOptionals() throws IOException {
        final Map<String, Schema> schemas = ModelConverters.getInstance().read(GuavaModel.class);
        final String json = ResourceUtils.loadClassResource(getClass(), "GuavaTestModel.json");
        SerializationMatchers.assertEqualsToJson(schemas, json);
    }
}
