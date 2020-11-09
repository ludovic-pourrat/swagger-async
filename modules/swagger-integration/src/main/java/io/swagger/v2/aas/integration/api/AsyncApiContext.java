package io.swagger.v2.aas.integration.api;

import java.util.Set;

import io.swagger.v2.aas.integration.AsyncApiConfigurationException;
import io.swagger.v2.aas.models.AsyncAPI;
import io.swagger.v2.async.converter.ModelConverter;

public interface AsyncApiContext {

    String ASYNCAPI_CONTEXT_ID_KEY = "asyncapi.context.id";
    String ASYNCAPI_CONTEXT_ID_PREFIX = ASYNCAPI_CONTEXT_ID_KEY + ".";
    String ASYNCAPI_CONTEXT_ID_DEFAULT = ASYNCAPI_CONTEXT_ID_PREFIX + "default";

    String getId();

    AsyncApiContext init() throws AsyncApiConfigurationException;

    AsyncAPI read();

    AsyncAPIConfiguration getAsyncApiConfiguration();

    String getConfigLocation();

    AsyncApiContext getParent();

    void setOpenApiScanner(AsyncApiScanner openApiScanner);

    void setAsyncApiReader(AsyncApiReader asyncApiReader);

    /**
     * @since 2.0.6
     */
    void setObjectMapperProcessor(ObjectMapperProcessor objectMapperProcessor);

    /**
     * @since 2.0.6
     */
    void setModelConverters(Set<ModelConverter> modelConverters);

}
