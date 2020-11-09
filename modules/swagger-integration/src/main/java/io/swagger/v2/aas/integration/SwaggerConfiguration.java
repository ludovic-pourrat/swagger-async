package io.swagger.v2.aas.integration;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import io.swagger.v2.aas.integration.api.AsyncAPIConfiguration;
import io.swagger.v2.aas.models.AsyncAPI;

public class SwaggerConfiguration implements AsyncAPIConfiguration {

    Map<String, Object> userDefinedOptions;
    private AsyncAPI asyncAPI;

    private String id;
    private Set<String> resourcePackages;
    private Set<String> resourceClasses;
    private String filterClass;
    private String readerClass;
    private String scannerClass;

    private Boolean prettyPrint;

    // read all operations also with no @Operation; set to false to read only methods annotated with @Operation
    private Boolean readAllResources = Boolean.TRUE;

    private Collection<String> ignoredRoutes;
    private Long cacheTTL = -1L;

    private Set<String> modelConverterClasses;
    private String objectMapperProcessorClass;

    public Long getCacheTTL() {
        return cacheTTL;
    }

    public void setCacheTTL(Long cacheTTL) {
        this.cacheTTL = cacheTTL;
    }

    public SwaggerConfiguration cacheTTL(Long cacheTTL) {
        this.cacheTTL = cacheTTL;
        return this;
    }

    public Boolean isReadAllResources() {
        return readAllResources;
    }

    public void setReadAllResources(Boolean readAllResources) {
        this.readAllResources = readAllResources;
    }

    public SwaggerConfiguration readAllResources(Boolean readAllResources) {
        this.readAllResources = readAllResources;
        return this;
    }

    public Collection<String> getIgnoredRoutes() {
        return ignoredRoutes;
    }

    public void setIgnoredRoutes(Collection<String> ignoredRoutes) {
        this.ignoredRoutes = ignoredRoutes;
    }

    public SwaggerConfiguration ignoredRoutes(Collection<String> ignoredRoutes) {
        this.ignoredRoutes = ignoredRoutes;
        return this;
    }

    public Boolean isPrettyPrint() {
        return prettyPrint;
    }

    public void setPrettyPrint(Boolean prettyPrint) {
        this.prettyPrint = prettyPrint;
    }

    public SwaggerConfiguration prettyPrint(Boolean prettyPrint) {
        this.prettyPrint = prettyPrint;
        return this;
    }

    @Override
    public AsyncAPI getAsyncAPI() {
        return asyncAPI;
    }

    public void setAsyncAPI(AsyncAPI asyncAPI) {
        this.asyncAPI = asyncAPI;
    }

    public SwaggerConfiguration asyncAPI(AsyncAPI asyncAPI) {
        this.asyncAPI = asyncAPI;
        return this;
    }

    public String getReaderClass() {
        return readerClass;
    }

    public void setReaderClass(String readerClass) {
        this.readerClass = readerClass;
    }

    public String getScannerClass() {
        return scannerClass;
    }

    public void setScannerClass(String scannerClass) {
        this.scannerClass = scannerClass;
    }

    public Map<String, Object> getUserDefinedOptions() {
        return userDefinedOptions;
    }

    public void setUserDefinedOptions(Map<String, Object> userDefinedOptions) {
        this.userDefinedOptions = userDefinedOptions;
    }

    public SwaggerConfiguration scannerClass(String scannerClass) {
        this.scannerClass = scannerClass;
        return this;
    }

    public SwaggerConfiguration readerClass(String readerClass) {
        this.readerClass = readerClass;
        return this;
    }

    public SwaggerConfiguration userDefinedOptions(Map<String, Object> userDefinedOptions) {
        this.userDefinedOptions = userDefinedOptions;
        return this;
    }

    @Override
    public Set<String> getResourcePackages() {
        return resourcePackages;
    }

    public void setResourcePackages(Set<String> resourcePackages) {
        this.resourcePackages = resourcePackages;
    }

    public SwaggerConfiguration resourcePackages(Set<String> resourcePackages) {
        this.resourcePackages = resourcePackages;
        return this;
    }

    public Set<String> getResourceClasses() {
        return resourceClasses;
    }

    public void setResourceClasses(Set<String> resourceClasses) {
        this.resourceClasses = resourceClasses;
    }

    public SwaggerConfiguration resourceClasses(Set<String> resourceClasses) {
        this.resourceClasses = resourceClasses;
        return this;
    }

    public String getFilterClass() {
        return filterClass;
    }

    public void setFilterClass(String filterClass) {
        this.filterClass = filterClass;
    }

    public SwaggerConfiguration filterClass(String filterClass) {
        this.filterClass = filterClass;
        return this;
    }

    public SwaggerConfiguration id(String id) {
        this.id = id;
        return this;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * @since 2.0.6
     */
    public SwaggerConfiguration objectMapperProcessorClass(String objectMapperProcessorClass) {
        this.objectMapperProcessorClass = objectMapperProcessorClass;
        return this;
    }

    /**
     * @since 2.0.6
     */
    public String getObjectMapperProcessorClass() {
        return objectMapperProcessorClass;
    }

    /**
     * @since 2.0.6
     */
    public void setObjectMapperProcessorClass(String objectMapperProcessorClass) {
        this.objectMapperProcessorClass = objectMapperProcessorClass;
    }

    /**
     * @since 2.0.6
     */
    public Set<String> getModelConverterClasses() {
        return modelConverterClasses;
    }

    /**
     * @since 2.0.6
     */
    public void setModelConverterClassess(Set<String> modelConverterClasses) {
        this.modelConverterClasses = modelConverterClasses;
    }

    /**
     * @since 2.0.6
     */
    public SwaggerConfiguration modelConverterClasses(Set<String> modelConverterClasses) {
        this.modelConverterClasses = modelConverterClasses;
        return this;
    }
}
