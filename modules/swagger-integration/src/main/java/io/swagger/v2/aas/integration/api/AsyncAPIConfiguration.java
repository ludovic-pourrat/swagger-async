package io.swagger.v2.aas.integration.api;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import io.swagger.v2.aas.models.AsyncAPI;

public interface AsyncAPIConfiguration {
    Set<String> getResourcePackages();

    Set<String> getResourceClasses();

    String getReaderClass();

    String getScannerClass();

    String getFilterClass();

    Collection<String> getIgnoredRoutes();

    AsyncAPI getAsyncAPI();

    Map<String, Object> getUserDefinedOptions();

    Boolean isReadAllResources();

    Boolean isPrettyPrint();

    Long getCacheTTL();

    /**
     * @since 2.0.6
     */
    public String getObjectMapperProcessorClass();

    /**
     * @since 2.0.6
     */
    public Set<String> getModelConverterClasses();

}
