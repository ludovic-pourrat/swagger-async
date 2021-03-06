package io.swagger.v2.async.resolving;

import io.swagger.v2.async.resolving.resources.TestObject2972;
import io.swagger.v2.async.converter.AnnotatedType;
import io.swagger.v2.async.converter.ModelConverterContextImpl;
import io.swagger.v2.async.jackson.ModelResolver;
import io.swagger.v2.async.matchers.SerializationMatchers;
import io.swagger.v2.aas.models.media.Schema;
import org.testng.annotations.Test;

public class Ticket2972Test extends SwaggerTestBase {

    @Test
    public void testLocalTime() throws Exception {

        final ModelResolver modelResolver = new ModelResolver(mapper());

        ModelConverterContextImpl context = new ModelConverterContextImpl(modelResolver);

        Schema model = context
                .resolve(new AnnotatedType(TestObject2972.class));

        SerializationMatchers.assertEqualsToYaml(context.getDefinedModels(), "TestObject2972:\n" +
                "  type: object\n" +
                "  properties:\n" +
                "    myField1:\n" +
                "      type: object\n" +
                "      additionalProperties:\n" +
                "        type: string\n" +
                "    myField2:\n" +
                "      type: object\n" +
                "      additionalProperties:\n" +
                "        type: string");
    }

}
