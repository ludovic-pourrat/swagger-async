package io.swagger.v2.async.util;

import com.fasterxml.jackson.databind.module.SimpleModule;
import io.swagger.v2.aas.models.Paths;
import io.swagger.v2.aas.models.callbacks.Callback;
import io.swagger.v2.aas.models.headers.Header;
import io.swagger.v2.aas.models.media.Encoding;
import io.swagger.v2.aas.models.media.EncodingProperty;
import io.swagger.v2.aas.models.media.Schema;
import io.swagger.v2.aas.models.parameters.Parameter;
import io.swagger.v2.aas.models.responses.ApiResponses;
import io.swagger.v2.aas.models.security.SecurityScheme;

public class DeserializationModule extends SimpleModule {

    public DeserializationModule() {

        this.addDeserializer(Schema.class, new ModelDeserializer());
        this.addDeserializer(Parameter.class, new ParameterDeserializer());
        this.addDeserializer(Header.StyleEnum.class, new HeaderStyleEnumDeserializer());
        this.addDeserializer(Encoding.StyleEnum.class, new EncodingStyleEnumDeserializer());
        this.addDeserializer(EncodingProperty.StyleEnum.class, new EncodingPropertyStyleEnumDeserializer());

        this.addDeserializer(SecurityScheme.class, new SecuritySchemeDeserializer());

        this.addDeserializer(ApiResponses.class, new ApiResponsesDeserializer());
        this.addDeserializer(Paths.class, new PathsDeserializer());
        this.addDeserializer(Callback.class, new CallbackDeserializer());
    }
}
