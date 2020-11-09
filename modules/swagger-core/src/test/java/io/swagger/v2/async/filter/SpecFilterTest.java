package io.swagger.v2.async.filter;

import static org.testng.Assert.*;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.google.common.collect.Sets;
import io.swagger.v2.aas.models.AsyncAPI;
import io.swagger.v2.aas.models.Components;
import io.swagger.v2.aas.models.Operation;
import io.swagger.v2.aas.models.PathItem;
import io.swagger.v2.aas.models.media.Schema;
import io.swagger.v2.aas.models.parameters.Parameter;
import io.swagger.v2.aas.models.tags.Tag;
import io.swagger.v2.async.filter.resources.ChangeGetOperationsFilter;
import io.swagger.v2.async.filter.resources.InternalModelPropertiesRemoverFilter;
import io.swagger.v2.async.filter.resources.NoGetOperationsFilter;
import io.swagger.v2.async.filter.resources.NoOpOperationsFilter;
import io.swagger.v2.async.filter.resources.NoAsyncAPIFilter;
import io.swagger.v2.async.filter.resources.NoParametersWithoutQueryInFilter;
import io.swagger.v2.async.filter.resources.NoPathItemFilter;
import io.swagger.v2.async.filter.resources.NoPetOperationsFilter;
import io.swagger.v2.async.filter.resources.NoPetRefSchemaFilter;
import io.swagger.v2.async.filter.resources.RemoveInternalParamsFilter;
import io.swagger.v2.async.filter.resources.RemoveUnreferencedDefinitionsFilter;
import io.swagger.v2.async.filter.resources.ReplaceGetOperationsFilter;
import io.swagger.v2.async.matchers.SerializationMatchers;
import io.swagger.v2.async.util.Json;
import io.swagger.v2.async.util.ResourceUtils;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.Test;

public class SpecFilterTest {

    private static final String RESOURCE_RECURSIVE_MODELS = "specFiles/recursivemodels.json";
    private static final String RESOURCE_PATH = "specFiles/petstore-3.0-v2.json";
    private static final String RESOURCE_PATH_3303 = "specFiles/petstore-3.0-v2-ticket-3303.json";
    private static final String RESOURCE_REFERRED_SCHEMAS = "specFiles/petstore-3.0-referred-schemas.json";
    private static final String RESOURCE_PATH_WITHOUT_MODELS = "specFiles/petstore-3.0-v2_withoutModels.json";
    private static final String RESOURCE_DEPRECATED_OPERATIONS = "specFiles/deprecatedoperationmodel.json";
    private static final String CHANGED_OPERATION_ID = "Changed Operation";
    private static final String CHANGED_OPERATION_DESCRIPTION = "Changing some attributes of the operation";
    private static final String NEW_OPERATION_ID = "New Operation";
    private static final String NEW_OPERATION_DESCRIPTION = "Replaced Operation";
    private static final String QUERY = "query";
    private static final String PET_MODEL = "Pet";
    private static final String TAG_MODEL = "/Tag";
    private static final String PET_TAG = "pet";
    private static final String STORE_TAG = "store";
    private static final String USER_TAG = "user";

    @Test(description = "it should clone everything")
    public void cloneEverything() throws IOException {
        final AsyncAPI asyncAPI = getAsyncAPI(RESOURCE_PATH);
        final AsyncAPI filtered = new SpecFilter().filter(asyncAPI, new NoOpOperationsFilter(), null, null, null);

        assertEquals(Json.pretty(filtered), Json.pretty(asyncAPI));
    }

    @Test(description = "it should filter away get operations in a resource")
    public void filterAwayGetOperations() throws IOException {
        final AsyncAPI asyncAPI = getAsyncAPI(RESOURCE_PATH);
        final NoGetOperationsFilter filter = new NoGetOperationsFilter();
        final AsyncAPI filtered = new SpecFilter().filter(asyncAPI, filter, null, null, null);

        if (filtered.getPaths() != null) {
            for (Map.Entry<String, PathItem> entry : filtered.getPaths().entrySet()) {
                assertNull(entry.getValue().getGet());
            }
        } else {
            fail("paths should not be null");
        }
    }

    @Test(description = "it should filter away the pet resource")
    public void filterAwayPetResource() throws IOException {
        final AsyncAPI asyncAPI = getAsyncAPI(RESOURCE_PATH);
        final NoPetOperationsFilter filter = new NoPetOperationsFilter();

        final AsyncAPI filtered = new SpecFilter().filter(asyncAPI, filter, null, null, null);
        if (filtered.getPaths() != null) {
            for (Map.Entry<String, PathItem> entry : filtered.getPaths().entrySet()) {
                assertNull(entry.getValue().getDelete());
                assertNull(entry.getValue().getPost());
                assertNull(entry.getValue().getPut());
                assertNull(entry.getValue().getGet());
                assertNull(entry.getValue().getHead());
                assertNull(entry.getValue().getOptions());
            }
        } else {
            fail("paths should not be null");
        }
    }

    @Test(description = "it should replace away with a new operation")
    public void replaceGetResources() throws IOException {
        final AsyncAPI asyncAPI = getAsyncAPI(RESOURCE_PATH);
        AsyncAPI filter = new SpecFilter().filter(asyncAPI, new ReplaceGetOperationsFilter(), null, null, null);
        assertOperations(filter, NEW_OPERATION_ID, NEW_OPERATION_DESCRIPTION);
    }

    @Test(description = "it should change away with a new operation")
    public void changeGetResources() throws IOException {
        final AsyncAPI asyncAPI = getAsyncAPI(RESOURCE_PATH);
        AsyncAPI filter = new SpecFilter().filter(asyncAPI, new ChangeGetOperationsFilter(), null, null, null);
        assertOperations(filter, CHANGED_OPERATION_ID, CHANGED_OPERATION_DESCRIPTION);
    }

    private void assertOperations(AsyncAPI filtered, String operationId, String description) {
        if (filtered.getPaths() != null) {
            for (Map.Entry<String, PathItem> entry : filtered.getPaths().entrySet()) {
                Operation get = entry.getValue().getGet();
                if (get != null) {
                    assertEquals(get.getOperationId(), operationId);
                    assertEquals(get.getDescription(), description);
                }
            }
        } else {
            fail("paths should not be null");
        }
    }

    @Test(description = "it should filter an openAPI object")
    public void filterAwayOpenAPI() throws IOException {
        final AsyncAPI asyncAPI = getAsyncAPI(RESOURCE_PATH);
        final AsyncAPI filtered = new SpecFilter().filter(asyncAPI, new NoAsyncAPIFilter(), null, null, null);
        assertNull(filtered);
    }

    @Test(description = "it should filter any PathItem objects without Ref")
    public void filterAwayPathItemWithoutRef() throws IOException {
        final AsyncAPI asyncAPI = getAsyncAPI(RESOURCE_PATH);
        final AsyncAPI filtered = new SpecFilter().filter(asyncAPI, new NoPathItemFilter(), null, null, null);
        assertEquals(0, filtered.getPaths().size());
    }

    @Test(description = "it should filter any query parameter")
    public void filterAwayQueryParameters() throws IOException {
        final AsyncAPI asyncAPI = getAsyncAPI(RESOURCE_PATH);
        final AsyncAPI filtered = new SpecFilter().filter(asyncAPI, new NoParametersWithoutQueryInFilter(), null, null, null);
        if (filtered.getPaths() != null) {
            for (Map.Entry<String, PathItem> entry : filtered.getPaths().entrySet()) {
                validateParameters(entry.getValue().getGet());
                validateParameters(entry.getValue().getPost());
                validateParameters(entry.getValue().getPut());
                validateParameters(entry.getValue().getPatch());
                validateParameters(entry.getValue().getHead());
                validateParameters(entry.getValue().getDelete());
                validateParameters(entry.getValue().getOptions());
            }
        }
    }

    private void validateParameters(Operation operation) {
        if (operation != null && operation.getParameters() != null) {
            for (Parameter parameter : operation.getParameters()) {
                assertNotEquals(QUERY, parameter.getIn());
            }
        }
    }

    @Test(description = "it should clone everything concurrently")
    public void cloneEverythingConcurrent() throws IOException {
        final AsyncAPI asyncAPI = getAsyncAPI(RESOURCE_PATH);

        ThreadGroup tg = new ThreadGroup("SpecFilterTest" + "|" + System.currentTimeMillis());
        final Map<String, AsyncAPI> filteredMap = new ConcurrentHashMap<>();
        for (int i = 0; i < 10; i++) {
            final int id = i;
            new Thread(tg, "SpecFilterTest") {
                public void run() {
                    try {
                        filteredMap.put("filtered " + id, new SpecFilter().filter(asyncAPI, new NoOpOperationsFilter(), null, null, null));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }

        new Thread(new FailureHandler(tg, filteredMap, asyncAPI)).start();
    }

    class FailureHandler implements Runnable {
        ThreadGroup tg;
        Map<String, AsyncAPI> filteredMap;
        private AsyncAPI asyncAPI;

        private FailureHandler(ThreadGroup tg, Map<String, AsyncAPI> filteredMap, AsyncAPI asyncAPI) {
            this.tg = tg;
            this.filteredMap = filteredMap;
            this.asyncAPI = asyncAPI;
        }

        @Override
        public void run() {
            try {
                Thread[] thds = new Thread[tg.activeCount()];
                tg.enumerate(thds);
                for (Thread t : thds) {
                    if (t != null) {
                        t.join(10000);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            for (AsyncAPI filtered : filteredMap.values()) {
                assertEquals(Json.pretty(asyncAPI), Json.pretty(filtered));
            }
        }
    }

    @Test(description = "it should clone everything from JSON without models")
    public void cloneWithoutModels() throws IOException {
        final String json = ResourceUtils.loadClassResource(getClass(), RESOURCE_PATH_WITHOUT_MODELS);
        final AsyncAPI asyncAPI = Json.mapper().readValue(json, AsyncAPI.class);
        final AsyncAPI filtered = new SpecFilter().filter(asyncAPI, new NoOpOperationsFilter(), null, null, null);

        SerializationMatchers.assertEqualsToJson(filtered, json);
    }

    @Test
    public void shouldRemoveBrokenRefs() throws IOException {
        final AsyncAPI asyncAPI = getAsyncAPI(RESOURCE_PATH);
        asyncAPI.getPaths().get("/pet/{petId}").getGet().getResponses().getDefault().getHeaders().remove("X-Rate-Limit-Limit");
        assertNotNull(asyncAPI.getComponents().getSchemas().get("PetHeader"));
        final RemoveUnreferencedDefinitionsFilter remover = new RemoveUnreferencedDefinitionsFilter();
        final AsyncAPI filtered = new SpecFilter().filter(asyncAPI, remover, null, null, null);

        assertNull(filtered.getComponents().getSchemas().get("PetHeader"));
        assertNotNull(filtered.getComponents().getSchemas().get("Category"));
        assertNotNull(filtered.getComponents().getSchemas().get("Pet"));
    }

    @Test
    public void shouldRemoveBrokenNestedRefs() throws IOException {
        final AsyncAPI asyncAPI = getAsyncAPI(RESOURCE_PATH_3303);
        asyncAPI.getPaths().get("/pet/{petId}").getGet().getResponses().getDefault().getHeaders().remove("X-Rate-Limit-Limit");
        assertNotNull(asyncAPI.getComponents().getSchemas().get("PetHeader"));
        final RemoveUnreferencedDefinitionsFilter remover = new RemoveUnreferencedDefinitionsFilter();
        final AsyncAPI filtered = new SpecFilter().filter(asyncAPI, remover, null, null, null);

        assertNull(filtered.getComponents().getSchemas().get("PetHeader"));
        assertNull(filtered.getComponents().getSchemas().get("Bar"));
        assertNotNull(filtered.getComponents().getSchemas().get("Category"));
        assertNotNull(filtered.getComponents().getSchemas().get("Pet"));
        assertNotNull(filtered.getComponents().getSchemas().get("Foo"));
        assertNotNull(filtered.getComponents().getSchemas().get("allOfChild"));
        assertNotNull(filtered.getComponents().getSchemas().get("anyOfChild"));
        assertNotNull(filtered.getComponents().getSchemas().get("oneOfChild"));
        assertNotNull(filtered.getComponents().getSchemas().get("allOfparentA"));
        assertNotNull(filtered.getComponents().getSchemas().get("allOfparentB"));
        assertNotNull(filtered.getComponents().getSchemas().get("anyOfparentA"));
        assertNotNull(filtered.getComponents().getSchemas().get("anyOfparentB"));
        assertNotNull(filtered.getComponents().getSchemas().get("oneOfparentA"));
        assertNotNull(filtered.getComponents().getSchemas().get("oneOfparentB"));
        assertNotNull(filtered.getComponents().getSchemas().get("oneOfNestedParentA"));
        assertNotNull(filtered.getComponents().getSchemas().get("oneOfNestedParentB"));
        assertNotNull(filtered.getComponents().getSchemas().get("discriminatorParent"));
        assertNotNull(filtered.getComponents().getSchemas().get("discriminatorMatchedChildA"));
        assertNotNull(filtered.getComponents().getSchemas().get("discriminatorRefProperty"));
        assertNotNull(filtered.getComponents().getSchemas().get("discriminatorParentRefProperty"));
        assertNotNull(filtered.getComponents().getSchemas().get("discriminatorMatchedChildB"));
    }

    @Test
    public void shouldNotRemoveGoodRefs() throws IOException {
        final AsyncAPI asyncAPI = getAsyncAPI(RESOURCE_PATH);
        assertNotNull(asyncAPI.getComponents().getSchemas().get("PetHeader"));
        final RemoveUnreferencedDefinitionsFilter remover = new RemoveUnreferencedDefinitionsFilter();
        final AsyncAPI filtered = new SpecFilter().filter(asyncAPI, remover, null, null, null);

        assertNotNull(filtered.getComponents().getSchemas().get("PetHeader"));
        assertNotNull(filtered.getComponents().getSchemas().get("Category"));
    }

    @Test(description = "it should filter any Pet Ref in Schemas")
    public void filterAwayPetRefInSchemas() throws IOException {
        final AsyncAPI asyncAPI = getAsyncAPI(RESOURCE_PATH);
        final AsyncAPI filtered = new SpecFilter().filter(asyncAPI, new NoPetRefSchemaFilter(), null, null, null);
        validateSchemasInComponents(filtered.getComponents(), PET_MODEL);
    }

    private void validateSchemasInComponents(Components components, String model) {
        if (components != null) {
            if (components.getSchemas() != null) {
                components.getSchemas().forEach((k, v) -> assertNotEquals(model, k));
            }
        }
    }

    @Test(description = "it should filter away secret parameters")
    public void filterAwaySecretParameters() throws IOException {
        final AsyncAPI asyncAPI = getAsyncAPI(RESOURCE_PATH);
        final RemoveInternalParamsFilter filter = new RemoveInternalParamsFilter();
        final AsyncAPI filtered = new SpecFilter().filter(asyncAPI, filter, null, null, null);

        if (filtered.getPaths() != null) {
            for (Map.Entry<String, PathItem> entry : filtered.getPaths().entrySet()) {
                final Operation get = entry.getValue().getGet();
                if (get != null) {
                    for (Parameter param : get.getParameters()) {
                        final String description = param.getDescription();
                        if (StringUtils.isNotBlank(description)) {
                            assertFalse(description.startsWith("secret"));
                        }
                    }
                }
            }
        } else {
            fail("paths should not be null");
        }
    }

    @Test(description = "it should filter away internal model properties")
    public void filterAwayInternalModelProperties() throws IOException {
        final AsyncAPI asyncAPI = getAsyncAPI(RESOURCE_PATH);
        final InternalModelPropertiesRemoverFilter filter = new InternalModelPropertiesRemoverFilter();

        final AsyncAPI filtered = new SpecFilter().filter(asyncAPI, filter, null, null, null);
        for (Map.Entry<String, Schema> entry : filtered.getComponents().getSchemas().entrySet()) {
            for (String propName : (Set<String>) entry.getValue().getProperties().keySet()) {
                assertFalse(propName.startsWith("_"));
            }
        }
    }

    @Test(description = "it should retain non-broken reference model composed properties")
    public void retainNonBrokenReferenceModelComposedProperties() throws IOException {
        final AsyncAPI asyncAPI = getAsyncAPI(RESOURCE_REFERRED_SCHEMAS);

        assertNotNull(asyncAPI.getComponents().getSchemas().get("User"));

        final NoOpOperationsFilter noOperationsFilter = new NoOpOperationsFilter();
        AsyncAPI filtered = new SpecFilter().filter(asyncAPI, noOperationsFilter, null, null, null);

        assertNotNull(filtered.getComponents().getSchemas().get("User"));

        final RemoveUnreferencedDefinitionsFilter refFilter = new RemoveUnreferencedDefinitionsFilter();
        filtered = new SpecFilter().filter(asyncAPI, refFilter, null, null, null);

        assertNotNull(filtered.getComponents().getSchemas().get("User"));
        assertNotNull(filtered.getComponents().getSchemas().get("Pet"));

    }

    @Test(description = "recursive models, e.g. A-> A or A-> B and B -> A should not result in stack overflow")
    public void removeUnreferencedDefinitionsOfRecuriveModels() throws IOException {
        final AsyncAPI asyncAPI = getAsyncAPI(RESOURCE_RECURSIVE_MODELS);
        final RemoveUnreferencedDefinitionsFilter remover = new RemoveUnreferencedDefinitionsFilter();
        final AsyncAPI filtered = new SpecFilter().filter(asyncAPI, remover, null, null, null);

        assertNotNull(filtered.getComponents().getSchemas().get("SelfReferencingModel"));
        assertNotNull(filtered.getComponents().getSchemas().get("IndirectRecursiveModelA"));
        assertNotNull(filtered.getComponents().getSchemas().get("IndirectRecursiveModelB"));
    }

    @Test(description = "broken references should not result in NPE")
    public void removeUnreferencedModelOverride() throws IOException {
        final AsyncAPI asyncAPI = getAsyncAPI(RESOURCE_REFERRED_SCHEMAS);
        final RemoveUnreferencedDefinitionsFilter remover = new RemoveUnreferencedDefinitionsFilter();
        final AsyncAPI filtered = new SpecFilter().filter(asyncAPI, remover, null, null, null);

        assertNotNull(filtered.getComponents().getSchemas().get("Order"));
    }

    @Test(description = "Retain models referenced from additonalProperties")
    public void retainModelsReferencesFromAdditionalProperties() throws IOException {
        final AsyncAPI asyncAPI = getAsyncAPI(RESOURCE_REFERRED_SCHEMAS);
        final RemoveUnreferencedDefinitionsFilter remover = new RemoveUnreferencedDefinitionsFilter();
        final AsyncAPI filtered = new SpecFilter().filter(asyncAPI, remover, null, null, null);

        assertNotNull(filtered.getComponents().getSchemas().get("Order"));
        assertNotNull(filtered.getComponents().getSchemas().get("ReferredOrder"));
    }

    @Test(description = "Clone should retain any 'deperecated' flags present on operations")
    public void cloneRetainDeperecatedFlags() throws IOException {
        final AsyncAPI asyncAPI = getAsyncAPI(RESOURCE_DEPRECATED_OPERATIONS);
        final RemoveUnreferencedDefinitionsFilter remover = new RemoveUnreferencedDefinitionsFilter();
        final AsyncAPI filtered = new SpecFilter().filter(asyncAPI, remover, null, null, null);

        Operation operation = filtered.getPaths().get("/test").getGet();

        Boolean deprectedFlag = operation.getDeprecated();
        assertNotNull(deprectedFlag);
        assertEquals(deprectedFlag, Boolean.TRUE);
    }

    @Test(description = "it should contain all tags in the top level OpenAPI object")
    public void shouldContainAllTopLevelTags() throws IOException {
        final AsyncAPI asyncAPI = getAsyncAPI(RESOURCE_REFERRED_SCHEMAS);
        final NoOpOperationsFilter filter = new NoOpOperationsFilter();
        final AsyncAPI filtered = new SpecFilter().filter(asyncAPI, filter, null, null, null);
        assertEquals(getTagNames(filtered), Sets.newHashSet(PET_TAG, USER_TAG, STORE_TAG));
    }

    @Test(description = "it should not contain user tags in the top level OpenAPI object")
    public void shouldNotContainTopLevelUserTags() throws IOException {
        final AsyncAPI asyncAPI = getAsyncAPI(RESOURCE_REFERRED_SCHEMAS);
        final NoPetOperationsFilter filter = new NoPetOperationsFilter();
        final AsyncAPI filtered = new SpecFilter().filter(asyncAPI, filter, null, null, null);
        assertEquals(getTagNames(filtered), Sets.newHashSet(USER_TAG, STORE_TAG));

    }

    @Test(description = "it should filter with null definitions")
    public void filterWithNullDefinitions() throws IOException {
        final AsyncAPI asyncAPI = getAsyncAPI(RESOURCE_PATH);
        asyncAPI.getComponents().setSchemas(null);

        final InternalModelPropertiesRemoverFilter filter = new InternalModelPropertiesRemoverFilter();
        final AsyncAPI filtered = new SpecFilter().filter(asyncAPI, filter, null, null, null);
        assertNotNull(filtered);
    }

    private Set getTagNames(AsyncAPI asyncAPI) {
        Set<String> result = new HashSet<>();
        if (asyncAPI.getTags() != null) {
            for (Tag item : asyncAPI.getTags()) {
                result.add(item.getName());
            }
        }
        return result;
    }

    private AsyncAPI getAsyncAPI(String path) throws IOException {
        final String json = ResourceUtils.loadClassResource(getClass(), path);
        return Json.mapper().readValue(json, AsyncAPI.class);
    }
}
