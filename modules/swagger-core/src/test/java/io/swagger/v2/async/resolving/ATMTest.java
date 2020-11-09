package io.swagger.v2.async.resolving;

import io.swagger.v2.async.converter.AnnotatedType;
import io.swagger.v2.async.converter.ModelConverterContextImpl;
import io.swagger.v2.async.jackson.ModelResolver;
import io.swagger.v2.aas.models.media.Schema;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;

public class ATMTest extends SwaggerTestBase {

    @Test
    public void testATMModel() throws Exception {
        final ModelResolver modelResolver = new ModelResolver(mapper());
        final ModelConverterContextImpl context = new ModelConverterContextImpl(modelResolver);

        final Schema model = context
                .resolve(new AnnotatedType(ATM.class));
        assertNotNull(model);
    }

    public enum Currency {USA, CANADA}

    static class ATM {
        private Currency currency;

        public Currency getCurrency() {
            return currency;
        }

        public void setCurrency(Currency currency) {
            this.currency = currency;
        }
    }
}

