package io.swagger.v2.async.resolving;

import io.swagger.v2.async.converter.AnnotatedType;
import io.swagger.v2.async.converter.ModelConverterContextImpl;
import io.swagger.v2.async.jackson.ModelResolver;
import io.swagger.v2.aas.models.media.Schema;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;

public class ComplexPropertyTest extends SwaggerTestBase {

    @Test
    public void testOuterBean() throws Exception {
        final ModelResolver modelResolver = modelResolver();
        final ModelConverterContextImpl context = new ModelConverterContextImpl(modelResolver);
        final Schema model = context
                .resolve(new AnnotatedType(OuterBean.class));
        assertNotNull(model);
    }

    static class OuterBean {
        public int counter;
        public InnerBean inner;
    }

  /*
  /**********************************************************
  /* Test methods
  /**********************************************************
   */

    static class InnerBean {
        public int d;
        public int a;
        public int c;
        public int b;
    }
}
