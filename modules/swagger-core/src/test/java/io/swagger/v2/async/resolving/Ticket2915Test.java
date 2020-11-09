package io.swagger.v2.async.resolving;

import io.swagger.v2.async.resolving.resources.TestObject2915;
import io.swagger.v2.async.converter.AnnotatedType;
import io.swagger.v2.async.converter.ModelConverterContextImpl;
import io.swagger.v2.async.jackson.ModelResolver;
import io.swagger.v2.async.matchers.SerializationMatchers;
import io.swagger.v2.aas.models.media.Schema;
import org.testng.annotations.Test;

public class Ticket2915Test extends SwaggerTestBase {
    @Test
    public void testPropertyName() throws Exception {
        final ModelResolver modelResolver = new ModelResolver(mapper());

        final ModelConverterContextImpl context = new ModelConverterContextImpl(modelResolver);

        final Schema model = context
                .resolve(new AnnotatedType(TestObject2915.class));

        SerializationMatchers.assertEqualsToYaml(context.getDefinedModels(), "QuantitativeValue:\n" +
                "  required:\n" +
                "  - value\n" +
                "  type: object\n" +
                "  properties:\n" +
                "    value:\n" +
                "      type: number\n" +
                "      format: double\n" +
                "    unitText:\n" +
                "      type: string\n" +
                "    unitCode:\n" +
                "      type: string\n" +
                "  description: A combination of a value and associated unit\n" +
                "TestObject2616:\n" +
                "  type: object\n" +
                "  properties:\n" +
                "    name:\n" +
                "      type: string\n" +
                "    perServing:\n" +
                "      $ref: '#/components/schemas/QuantitativeValue'\n" +
                "    per100Gram:\n" +
                "      $ref: '#/components/schemas/QuantitativeValue'\n" +
                "  description: Nutritional value specification");
    }

}
