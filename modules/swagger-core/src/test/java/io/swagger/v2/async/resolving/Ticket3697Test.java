package io.swagger.v2.async.resolving;

import io.swagger.v2.async.resolving.resources.TestObject3697;
import io.swagger.v2.async.converter.AnnotatedType;
import io.swagger.v2.async.converter.ModelConverterContextImpl;
import io.swagger.v2.async.jackson.ModelResolver;
import io.swagger.v2.async.matchers.SerializationMatchers;
import io.swagger.v2.aas.models.media.Schema;
import org.testng.annotations.Test;

public class Ticket3697Test extends SwaggerTestBase {

    @Test
    public void testHiddenJsonCreator() throws Exception {

        final ModelResolver modelResolver = new ModelResolver(mapper());

        ModelConverterContextImpl context = new ModelConverterContextImpl(modelResolver);

        Schema model = context
                .resolve(new AnnotatedType(TestObject3697.class));

        SerializationMatchers.assertEqualsToYaml(context.getDefinedModels(), "TestObject3697:\n" +
                "  type: object\n" +
                "  properties:\n" +
                "    id:\n" +
                "      type: integer\n" +
                "      format: int64");
    }

}
