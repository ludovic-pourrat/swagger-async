package io.swagger.v2.async.resolving;

import io.swagger.v2.async.resolving.resources.Ticket2862Model;
import io.swagger.v2.async.converter.AnnotatedType;
import io.swagger.v2.async.converter.ModelConverterContextImpl;
import io.swagger.v2.async.jackson.ModelResolver;
import io.swagger.v2.async.matchers.SerializationMatchers;
import io.swagger.v2.aas.models.media.Schema;
import org.testng.annotations.Test;

public class Ticket2862SubtypeTest extends SwaggerTestBase {
    @Test
    public void testSubType() throws Exception {
        final ModelResolver modelResolver = new ModelResolver(mapper());

        final ModelConverterContextImpl context = new ModelConverterContextImpl(modelResolver);

        final Schema model = context
                .resolve(new AnnotatedType(Ticket2862Model.class));

        SerializationMatchers.assertEqualsToYaml(context.getDefinedModels(), "Ticket2862Model:\n" +
                "  type: object\n" +
                "Ticket2862ModelImpl:\n" +
                "  type: string\n" +
                "  allOf:\n" +
                "  - $ref: '#/components/schemas/Ticket2862Model'\n" +
                "  enum:\n" +
                "  - VALUE1\n" +
                "  - VALUE2");
    }

}
