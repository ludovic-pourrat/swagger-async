package io.swagger.v2.aas.integration;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v2.aas.integration.api.AsyncAPIConfiguration;
import io.swagger.v2.aas.integration.api.AsyncApiConfigurationLoader;
import io.swagger.v2.aas.integration.api.AsyncApiContext;
import io.swagger.v2.aas.integration.api.AsyncApiReader;
import io.swagger.v2.aas.integration.api.AsyncApiScanner;
import io.swagger.v2.aas.integration.api.ObjectMapperProcessor;
import io.swagger.v2.aas.models.AsyncAPI;
import io.swagger.v2.async.converter.ModelConverter;
import io.swagger.v2.async.converter.ModelConverters;
import io.swagger.v2.async.jackson.ModelResolver;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenericAsyncApiContext<T extends GenericAsyncApiContext> implements AsyncApiContext {

    private static Logger LOGGER = LoggerFactory.getLogger(GenericAsyncApiContext.class);

    protected Set<String> resourcePackages;
    protected Set<String> resourceClasses;
    protected String id = ASYNCAPI_CONTEXT_ID_DEFAULT;
    protected AsyncApiContext parent;
    protected String configLocation;
    private AsyncAPIConfiguration asyncApiConfiguration;

    private AsyncApiReader asyncApiReader;
    private AsyncApiScanner openApiScanner;
    private AsyncApiReader providedAsyncApiReader;

    private ObjectMapperProcessor objectMapperProcessor;
    private Set<ModelConverter> modelConverters;

    private ConcurrentHashMap<String, Cache> cache = new ConcurrentHashMap<>();

    // 0 doesn't cache
    // -1 perpetual
    private long cacheTTL = -1;

    public long getCacheTTL() {
        return cacheTTL;
    }

    public void setCacheTTL(long cacheTTL) {
        this.cacheTTL = cacheTTL;
    }

    public T cacheTTL(long cacheTTL) {
        this.cacheTTL = cacheTTL;
        return (T) this;
    }

    public AsyncApiReader getAsyncApiReader() {
        return asyncApiReader;
    }

    @Override
    public void setAsyncApiReader(AsyncApiReader asyncApiReader) {
        this.asyncApiReader = asyncApiReader;
        providedAsyncApiReader = asyncApiReader;
    }

    public AsyncApiScanner getOpenApiScanner() {
        return openApiScanner;
    }

    @Override
    public void setOpenApiScanner(AsyncApiScanner openApiScanner) {
        this.openApiScanner = openApiScanner;
    }

    public final T openApiReader(AsyncApiReader asyncApiReader) {
        setAsyncApiReader(asyncApiReader);
        return (T) this;
    }

    public final T openApiScanner(AsyncApiScanner openApiScanner) {
        this.openApiScanner = openApiScanner;
        return (T) this;
    }

    public Set<String> getResourcePackages() {
        return resourcePackages;
    }

    public void setResourcePackages(Set<String> resourcePackages) {
        this.resourcePackages = resourcePackages;
    }

    public T resourcePackages(Set<String> resourcePackages) {
        this.resourcePackages = resourcePackages;
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

    public T openApiConfiguration(AsyncAPIConfiguration asyncApiConfiguration) {
        this.asyncApiConfiguration = asyncApiConfiguration;
        return (T) this;
    }

    @Override
    public String getConfigLocation() {
        return configLocation;
    }

    public void setConfigLocation(String configLocation) {
        this.configLocation = configLocation;
    }

    public final T configLocation(String configLocation) {
        this.configLocation = configLocation;
        return (T) this;
    }

    @Override
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public final T id(String id) {
        this.id = id;
        return (T) this;
    }

    @Override
    public AsyncApiContext getParent() {
        return this.parent;
    }

    public void setParent(AsyncApiContext parent) {
        this.parent = parent;
    }

    public final T parent(AsyncApiContext parent) {
        this.parent = parent;
        return (T) this;
    }

    /**
     * @since 2.0.6
     */
    public ObjectMapperProcessor getObjectMapperProcessor() {
        return objectMapperProcessor;
    }

    /**
     * @since 2.0.6
     */
    @Override
    public void setObjectMapperProcessor(ObjectMapperProcessor objectMapperProcessor) {
        this.objectMapperProcessor = objectMapperProcessor;
    }

    /**
     * @since 2.0.6
     */
    public final T objectMapperProcessor(ObjectMapperProcessor objectMapperProcessor) {
        this.objectMapperProcessor = objectMapperProcessor;
        return (T) this;
    }

    /**
     * @since 2.0.6
     */
    public Set<ModelConverter> getModelConverters() {
        return modelConverters;
    }

    /**
     * @since 2.0.6
     */
    @Override
    public void setModelConverters(Set<ModelConverter> modelConverters) {
        this.modelConverters = modelConverters;
    }

    /**
     * @since 2.0.6
     */
    public final T modelConverters(Set<ModelConverter> modelConverters) {
        this.modelConverters = modelConverters;
        return (T) this;
    }


    protected void register() {
        AsyncApiContextLocator.getInstance().putOpenApiContext(id, this);
    }

    @Override
    public AsyncAPIConfiguration getAsyncApiConfiguration() {
        return asyncApiConfiguration;
    }

    public void setAsyncApiConfiguration(AsyncAPIConfiguration asyncApiConfiguration) {
        this.asyncApiConfiguration = asyncApiConfiguration;
    }

    protected AsyncApiReader buildReader(final AsyncAPIConfiguration asyncApiConfiguration) throws Exception {
        AsyncApiReader reader;
        if (StringUtils.isNotBlank(asyncApiConfiguration.getReaderClass())) {
            Class cls = getClass().getClassLoader().loadClass(asyncApiConfiguration.getReaderClass());
            reader = (AsyncApiReader) cls.newInstance();
        } else {
            reader = new AsyncApiReader() {

                AsyncAPIConfiguration asyncApiConfiguration;

                @Override
                public void setConfiguration(AsyncAPIConfiguration asyncApiConfiguration) {
                    this.asyncApiConfiguration = asyncApiConfiguration;
                }

                @Override
                public AsyncAPI read(Set<Class<?>> classes, Map<String, Object> resources) {
                    return this.asyncApiConfiguration.getAsyncAPI();
                }
            };
        }
        reader.setConfiguration(asyncApiConfiguration);
        return reader;
    }

    protected AsyncApiScanner buildScanner(final AsyncAPIConfiguration asyncApiConfiguration) throws Exception {
        AsyncApiScanner scanner;
        if (StringUtils.isNotBlank(asyncApiConfiguration.getScannerClass())) {
            Class cls = getClass().getClassLoader().loadClass(asyncApiConfiguration.getScannerClass());
            scanner = (AsyncApiScanner) cls.newInstance();
        } else {
            scanner = new GenericAsyncApiScanner();
        }
        scanner.setConfiguration(asyncApiConfiguration);
        return scanner;
    }

    protected ObjectMapperProcessor buildObjectMapperProcessor(final AsyncAPIConfiguration asyncApiConfiguration) throws Exception {
        ObjectMapperProcessor objectMapperProcessor = null;
        if (StringUtils.isNotBlank(asyncApiConfiguration.getObjectMapperProcessorClass())) {
            Class cls = getClass().getClassLoader().loadClass(asyncApiConfiguration.getObjectMapperProcessorClass());
            objectMapperProcessor = (ObjectMapperProcessor) cls.newInstance();
        }
        return objectMapperProcessor;
    }

    protected Set<ModelConverter> buildModelConverters(final AsyncAPIConfiguration asyncApiConfiguration) throws Exception {
        if (asyncApiConfiguration.getModelConverterClasses() != null && !asyncApiConfiguration.getModelConverterClasses().isEmpty()) {
            LinkedHashSet<ModelConverter> modelConverters = new LinkedHashSet<>();
            for (String converterClass: asyncApiConfiguration.getModelConverterClasses()) {
                Class cls = getClass().getClassLoader().loadClass(converterClass);
                ModelConverter converter = (ModelConverter) cls.newInstance();
                modelConverters.add(converter);
            }
            return modelConverters;
        }
        return null;
    }

    protected List<ImmutablePair<String, String>> getKnownLocations() {
        return Arrays.asList(
                new ImmutablePair<>("classpath", "openapi-configuration.yaml"),
                new ImmutablePair<>("classpath", "openapi-configuration.json"),
                new ImmutablePair<>("file", "openapi-configuration.yaml"),
                new ImmutablePair<>("file", "openapi-configuration.json"),
                new ImmutablePair<>("classpath", "openapi.yaml"),
                new ImmutablePair<>("classpath", "openapi.json"),
                new ImmutablePair<>("file", "openapi.yaml"),
                new ImmutablePair<>("file", "openapi.json"),
                new ImmutablePair<>("service", "")
        );
    }

    protected Map<String, AsyncApiConfigurationLoader> getLocationLoaders() {
        Map<String, AsyncApiConfigurationLoader> map = new HashMap<>();
        map.put("classpath", new ClasspathAsyncApiConfigurationLoader());
        map.put("file", new FileAsyncApiConfigurationLoader());
        map.put("url", new URLAsyncApiConfigurationLoader());
        map.put("service", new ServiceAsyncApiConfigurationLoader());
        return map;
    }

    protected AsyncAPIConfiguration loadConfiguration() throws AsyncApiConfigurationException {

        Map<String, AsyncApiConfigurationLoader> loaders = getLocationLoaders();
        try {

            if (StringUtils.isNotEmpty(configLocation)) {
                if (loaders.get("classpath").exists(configLocation)) {
                    return loaders.get("classpath").load(configLocation);
                }
                if (loaders.get("file").exists(configLocation)) {
                    return loaders.get("file").load(configLocation);
                }
            }
            // check known locations
            List<ImmutablePair<String, String>> knownLocations = getKnownLocations();
            for (ImmutablePair<String, String> location : knownLocations) {
                if (loaders.get(location.left).exists(location.right)) {
                    try {
                        return loaders.get(location.left).load(location.right);
                    } catch (IOException ioe) {
                        // try next one
                    }
                }
            }
            return null;
        } catch (Exception e) {
            throw new AsyncApiConfigurationException(e.getMessage(), e);
        }
    }

    @Override
    public T init() throws AsyncApiConfigurationException {

        if (asyncApiConfiguration == null) {
            asyncApiConfiguration = loadConfiguration();
        }

        if (asyncApiConfiguration == null) {
            asyncApiConfiguration = new SwaggerConfiguration().resourcePackages(resourcePackages).resourceClasses(resourceClasses);
            ((SwaggerConfiguration) asyncApiConfiguration).setId(id);
        }

        asyncApiConfiguration = mergeParentConfiguration(asyncApiConfiguration, parent);

        try {
            if (asyncApiReader == null) {
                asyncApiReader = buildReader(ContextUtils.deepCopy(asyncApiConfiguration));
            }
            if (openApiScanner == null) {
                openApiScanner = buildScanner(ContextUtils.deepCopy(asyncApiConfiguration));
            }
            if (objectMapperProcessor == null) {
                objectMapperProcessor = buildObjectMapperProcessor(ContextUtils.deepCopy(asyncApiConfiguration));
            }
            if (modelConverters == null || modelConverters.isEmpty()) {
                modelConverters = buildModelConverters(ContextUtils.deepCopy(asyncApiConfiguration));
            }
        } catch (Exception e) {
            LOGGER.error("error initializing context: " + e.getMessage(), e);
            throw new AsyncApiConfigurationException("error initializing context: " + e.getMessage(), e);
        }

        try {
            if (objectMapperProcessor != null) {
                ObjectMapper mapper = IntegrationObjectMapperFactory.createJson();
                objectMapperProcessor.processJsonObjectMapper(mapper);
                ModelConverters.getInstance().addConverter(new ModelResolver(mapper));
            }
        } catch (Exception e) {
            LOGGER.error("error configuring objectMapper: " + e.getMessage(), e);
            throw new AsyncApiConfigurationException("error configuring objectMapper: " + e.getMessage(), e);
        }

        try {
            if (modelConverters != null && !modelConverters.isEmpty()) {
                for (ModelConverter converter: modelConverters) {
                    ModelConverters.getInstance().addConverter(converter);
                }
            }
        } catch (Exception e) {
            LOGGER.error("error configuring model converters: " + e.getMessage(), e);
            throw new AsyncApiConfigurationException("error configuring model converters: " + e.getMessage(), e);
        }

        // set cache TTL if present in configuration
        if (asyncApiConfiguration.getCacheTTL() != null) {
            this.cacheTTL = asyncApiConfiguration.getCacheTTL();
        }
        register();
        return (T) this;
    }

    private AsyncAPIConfiguration mergeParentConfiguration(AsyncAPIConfiguration config, AsyncApiContext parent) {
        if (parent == null || parent.getAsyncApiConfiguration() == null) {
            return config;
        }
        AsyncAPIConfiguration parentConfig = parent.getAsyncApiConfiguration();

        SwaggerConfiguration merged = null;

        if (config instanceof SwaggerConfiguration) {
            merged = (SwaggerConfiguration) config;
        } else {
            merged = (SwaggerConfiguration) ContextUtils.deepCopy(config);
        }

        if (merged.getResourceClasses() == null) {
            merged.setResourceClasses(parentConfig.getResourceClasses());
        }
        if (merged.getFilterClass() == null) {
            merged.setFilterClass(parentConfig.getFilterClass());
        }
        if (merged.getIgnoredRoutes() == null) {
            merged.setIgnoredRoutes(parentConfig.getIgnoredRoutes());
        }
        if (merged.getAsyncAPI() == null) {
            merged.setAsyncAPI(parentConfig.getAsyncAPI());
        }
        if (merged.getReaderClass() == null) {
            merged.setReaderClass(parentConfig.getReaderClass());
        }
        if (merged.getResourcePackages() == null) {
            merged.setResourcePackages(parentConfig.getResourcePackages());
        }
        if (merged.getScannerClass() == null) {
            merged.setScannerClass(parentConfig.getScannerClass());
        }
        if (merged.getCacheTTL() == null) {
            merged.setCacheTTL(parentConfig.getCacheTTL());
        }
        if (merged.getUserDefinedOptions() == null) {
            merged.setUserDefinedOptions(parentConfig.getUserDefinedOptions());
        }
        if (merged.isPrettyPrint() == null) {
            merged.setPrettyPrint(parentConfig.isPrettyPrint());
        }
        if (merged.isReadAllResources() == null) {
            merged.setReadAllResources(parentConfig.isReadAllResources());
        }
        if (merged.getObjectMapperProcessorClass() == null) {
            merged.setObjectMapperProcessorClass(parentConfig.getObjectMapperProcessorClass());
        }
        if (merged.getModelConverterClasses() == null) {
            merged.setModelConverterClassess(parentConfig.getModelConverterClasses());
        }

        return merged;
    }

    @Override
    public AsyncAPI read() {

        if (cacheTTL == 0) {
            resetReader();
            return getAsyncApiReader().read(getOpenApiScanner().classes(), getOpenApiScanner().resources());
        }
        Cache cached = cache.get("openapi");
        if (cached == null || cached.isStale(cacheTTL)) {
            cached = new Cache();
            cached.createdAt = System.currentTimeMillis();
            resetReader();
            cached.asyncAPI = getAsyncApiReader().read(getOpenApiScanner().classes(), getOpenApiScanner().resources());
            cache.put("openapi", cached);
        }
        return cached.asyncAPI;
    }

    protected void resetReader() {
        if (providedAsyncApiReader == null) {
            try {
                asyncApiReader = buildReader(ContextUtils.deepCopy(asyncApiConfiguration));
            } catch (Exception e) {
                LOGGER.error("error building reader: " + e.getMessage(), e);
                // keep previous reader
            }
        }
    }

    static class Cache {
        long createdAt = 0;
        AsyncAPI asyncAPI;

        boolean isStale(long cacheTTL) {
            return (cacheTTL > 0 && System.currentTimeMillis() - createdAt > cacheTTL);
        }
    }

}
