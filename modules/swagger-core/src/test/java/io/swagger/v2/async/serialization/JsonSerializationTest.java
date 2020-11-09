package io.swagger.v2.async.serialization;

import static org.testng.Assert.*;

import io.swagger.v2.aas.models.AsyncAPI;
import io.swagger.v2.aas.models.Operation;
import io.swagger.v2.aas.models.PathItem;
import io.swagger.v2.aas.models.info.Info;
import io.swagger.v2.aas.models.responses.ApiResponse;
import io.swagger.v2.aas.models.responses.ApiResponses;
import io.swagger.v2.aas.models.servers.Server;
import io.swagger.v2.async.util.Json;
import org.testng.annotations.Test;

public class JsonSerializationTest {

    @Test
    public void testSerializeASpecWithPathReferences() throws Exception {

        AsyncAPI swagger = new AsyncAPI()
                .addServersItem(new Server().url("http://petstore.swagger.io"));

        PathItem expectedPath = new PathItem().$ref("http://my.company.com/paths/health.json");
        swagger.path("/health", expectedPath);

        String swaggerJson = Json.mapper().writeValueAsString(swagger);
        AsyncAPI rebuilt = Json.mapper().readValue(swaggerJson, AsyncAPI.class);

        final PathItem path = rebuilt.getPaths().get("/health");
        assertEquals(path, expectedPath);
    }

    @Test
    public void testExtension() throws Exception {

        AsyncAPI swagger = new AsyncAPI();
        swagger.addExtension("x-foo-bar", "foo bar");
        swagger.setInfo(new Info());

        String swaggerJson = Json.mapper().writeValueAsString(swagger);
        assertFalse(swaggerJson.contains("extensions"));
        AsyncAPI rebuilt = Json.mapper().readValue(swaggerJson, AsyncAPI.class);
        assertEquals(rebuilt.getExtensions().values().iterator().next(), "foo bar");

    }

    @Test
    public void testSerializeASpecWithResponseReferences() throws Exception {
        AsyncAPI swagger = new AsyncAPI()
                .addServersItem(new Server().url("http://petstore.swagger.io"));

        ApiResponse expectedResponse = new ApiResponse().$ref("http://my.company.com/paths/health.json");
        PathItem expectedPath = new PathItem()
                .get(
                        new Operation().responses(
                                new ApiResponses()
                                        .addApiResponse("200", expectedResponse)));

        swagger.path("/health", expectedPath);

        String swaggerJson = Json.mapper().writeValueAsString(swagger);
        AsyncAPI rebuilt = Json.mapper().readValue(swaggerJson, AsyncAPI.class);

        assertEquals(rebuilt.getPaths().get("/health").getGet().getResponses().get("200"), expectedResponse);

    }
}
