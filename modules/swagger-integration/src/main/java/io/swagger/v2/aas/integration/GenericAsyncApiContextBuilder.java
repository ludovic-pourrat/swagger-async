package io.swagger.v2.aas.integration;

import java.util.Set;

import io.swagger.v2.aas.integration.api.AsyncAPIConfiguration;
import io.swagger.v2.aas.integration.api.AsyncApiContext;
import io.swagger.v2.aas.integration.api.AsyncApiContextBuilder;
import org.apache.commons.lang3.StringUtils;

public class GenericAsyncApiContextBuilder<T extends GenericAsyncApiContextBuilder> implements AsyncApiContextBuilder {

    protected String ctxId;

    protected String configLocation;
    protected Set<String> resourcePackages;
    protected Set<String> resourceClasses;
    protected AsyncAPIConfiguration asyncApiConfiguration;

    @Override
    public AsyncApiContext buildContext(boolean init) throws AsyncApiConfigurationException {
        if (StringUtils.isBlank(ctxId)) {
            ctxId = AsyncApiContext.ASYNCAPI_CONTEXT_ID_DEFAULT;
        }

        AsyncApiContext ctx = AsyncApiContextLocator.getInstance().getOpenApiContext(ctxId);

        if (ctx == null) {
            AsyncApiContext rootCtx = AsyncApiContextLocator.getInstance().getOpenApiContext(AsyncApiContext.ASYNCAPI_CONTEXT_ID_DEFAULT);
            ctx = new GenericAsyncApiContext()
                    .openApiConfiguration(asyncApiConfiguration)
                    .id(ctxId)
                    .parent(rootCtx);

            if (ctx.getConfigLocation() == null && configLocation != null) {
                ((GenericAsyncApiContext) ctx).configLocation(configLocation);
            }
            if (((GenericAsyncApiContext) ctx).getResourcePackages() == null && resourcePackages != null) {
                ((GenericAsyncApiContext) ctx).resourcePackages(resourcePackages);
            }
            if (((GenericAsyncApiContext) ctx).getResourceClasses() == null && resourceClasses != null) {
                ((GenericAsyncApiContext) ctx).resourceClasses(resourceClasses);
            }
            if (init) {
                ctx.init(); // includes registering itself with OpenApiContextLocator
            }
        }
        return ctx;
    }

    public String getCtxId() {
        return ctxId;
    }

    public void setCtxId(String ctxId) {
        this.ctxId = ctxId;
    }

    public String getConfigLocation() {
        return configLocation;
    }

    public void setConfigLocation(String configLocation) {
        this.configLocation = configLocation;
    }

    public Set<String> getResourcePackages() {
        return resourcePackages;
    }

    public void setResourcePackages(Set<String> resourcePackages) {
        this.resourcePackages = resourcePackages;
    }

    public AsyncAPIConfiguration getAsyncApiConfiguration() {
        return asyncApiConfiguration;
    }

    public void setAsyncApiConfiguration(AsyncAPIConfiguration asyncApiConfiguration) {
        this.asyncApiConfiguration = asyncApiConfiguration;
    }

    public T ctxId(String ctxId) {
        this.ctxId = ctxId;
        return (T) this;
    }

    public T configLocation(String configLocation) {
        this.configLocation = configLocation;
        return (T) this;
    }

    public T resourcePackages(Set<String> resourcePackages) {
        this.resourcePackages = resourcePackages;
        return (T) this;
    }

    public T openApiConfiguration(AsyncAPIConfiguration asyncApiConfiguration) {
        this.asyncApiConfiguration = asyncApiConfiguration;
        return (T) this;
    }

    public Set<String> getResourceClasses() {
        return resourceClasses;
    }

    public void setResourceClasses(Set<String> resourceClasses) {
        this.resourceClasses = resourceClasses;
    }

    public T resourceClasses(Set<String> resourceClasses) {
        this.resourceClasses = resourceClasses;
        return (T) this;
    }

}
