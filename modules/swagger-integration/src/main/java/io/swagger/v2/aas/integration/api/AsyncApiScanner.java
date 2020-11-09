package io.swagger.v2.aas.integration.api;

import java.util.Map;
import java.util.Set;

public interface AsyncApiScanner {

    void setConfiguration(AsyncAPIConfiguration asyncApiConfiguration);

    Set<Class<?>> classes();

    Map<String, Object> resources();

}
