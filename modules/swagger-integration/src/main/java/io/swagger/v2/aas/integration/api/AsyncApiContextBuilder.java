package io.swagger.v2.aas.integration.api;

import io.swagger.v2.aas.integration.AsyncApiConfigurationException;

public interface AsyncApiContextBuilder {

    AsyncApiContext buildContext(boolean init) throws AsyncApiConfigurationException;
}
