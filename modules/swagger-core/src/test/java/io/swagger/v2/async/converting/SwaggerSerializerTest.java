package io.swagger.v2.async.converting;

import static org.testng.Assert.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v2.aas.models.Components;
import io.swagger.v2.aas.models.AsyncAPI;
import io.swagger.v2.aas.models.Operation;
import io.swagger.v2.aas.models.PathItem;
import io.swagger.v2.aas.models.Paths;
import io.swagger.v2.aas.models.info.Contact;
import io.swagger.v2.aas.models.info.Info;
import io.swagger.v2.aas.models.links.Link;
import io.swagger.v2.aas.models.media.Content;
import io.swagger.v2.aas.models.media.IntegerSchema;
import io.swagger.v2.aas.models.media.MediaType;
import io.swagger.v2.aas.models.media.Schema;
import io.swagger.v2.aas.models.media.StringSchema;
import io.swagger.v2.aas.models.parameters.Parameter;
import io.swagger.v2.aas.models.parameters.QueryParameter;
import io.swagger.v2.aas.models.parameters.RequestBody;
import io.swagger.v2.aas.models.responses.ApiResponse;
import io.swagger.v2.aas.models.responses.ApiResponses;
import io.swagger.v2.aas.models.servers.Server;
import io.swagger.v2.async.converter.ModelConverters;
import io.swagger.v2.async.matchers.SerializationMatchers;
import io.swagger.v2.async.oas.models.Person;
import io.swagger.v2.async.util.Json;
import io.swagger.v2.async.util.OutputReplacer;
import io.swagger.v2.async.util.ResourceUtils;
import org.testng.annotations.Test;

public class SwaggerSerializerTest {
    ObjectMapper m = Json.mapper();

    @Test(description = "it should convert a spec")
    public void convertSpec() throws IOException {
        final Schema personModel = ModelConverters.getInstance().read(Person.class).get("Person");
        final Schema errorModel = ModelConverters.getInstance().read(Error.class).get("Error");
        final Info info = new Info()
                .version("1.0.0")
                .title("Swagger Petstore");

        final Contact contact = new Contact()
                .name("Swagger API Team")
                .email("foo@bar.baz")
                .url("http://swagger.io");

        info.setContact(contact);

        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "value");

        final AsyncAPI swagger = new AsyncAPI()
                .info(info)
                .addServersItem(new Server()
                        .url("http://petstore.swagger.io"))

//                .securityDefinition("api-key", new ApiKeyAuthDefinition("key", In.HEADER))
//                .consumes("application/json")
//                .produces("application/json")
                .schema("Person", personModel)
                .schema("Error", errorModel);

        final Operation get = new Operation()
//                .produces("application/json")
                .summary("finds pets in the system")
                .description("a longer description")
                .addTagsItem("Pet Operations")
                .operationId("get pet by id")
                .deprecated(true);

        get.addParametersItem(new Parameter()
                .in("query")
                .name("tags")
                .description("tags to filter by")
                .required(false)
                .schema(new StringSchema())
        );

        get.addParametersItem(new Parameter()
                .in("path")
                .name("petId")
                .description("pet to fetch")
                .schema(new IntegerSchema().format("int64"))
        );

        final ApiResponse response = new ApiResponse()
                .description("pets returned")
                .content(new Content()
                        .addMediaType("application/json", new MediaType()
                                .schema(new Schema().$ref("Person"))
                                .example("fun")));

        final ApiResponse errorResponse = new ApiResponse()
                .description("error response")
                .link("myLink", new Link()
                        .description("a link")
                        .operationId("theLinkedOperationId")
                        .parameters("userId", "gah")
                )
                .content(new Content()
                        .addMediaType("application/json", new MediaType()
                                .schema(new Schema().$ref("Error"))));

        get.responses(new ApiResponses()
                .addApiResponse("200", response)
                .addApiResponse("default", errorResponse));

        final Operation post = new Operation()
                .summary("adds a new pet")
                .description("you can add a new pet this way")
                .addTagsItem("Pet Operations")
                .operationId("add pet")
                .responses(new ApiResponses()
                        .addApiResponse("default", errorResponse))
                .requestBody(new RequestBody()
                        .description("the pet to add")
                        .content(new Content().addMediaType("*/*", new MediaType()
                                .schema(new Schema().$ref("Person")))));

        swagger.paths(new Paths().addPathItem("/pets", new PathItem()
                .get(get).post(post)));
        final String swaggerJson = Json.mapper().writeValueAsString(swagger);
        Json.prettyPrint(swagger);
        final AsyncAPI rebuilt = Json.mapper().readValue(swaggerJson, AsyncAPI.class);
        SerializationMatchers.assertEqualsToJson(rebuilt, swaggerJson);
    }

    @Test(description = "it should read the uber api")
    public void readUberApi() throws IOException {
        final String jsonString = ResourceUtils.loadClassResource(getClass(), "uber.json");
        final AsyncAPI swagger = Json.mapper().readValue(jsonString, AsyncAPI.class);
        assertNotNull(swagger);
    }

    @Test(description = "it should write a spec with parameter references")
    public void writeSpecWithParameterReferences() throws IOException {
        final Schema personModel = ModelConverters.getInstance().read(Person.class).get("Person");

        final Info info = new Info()
                .version("1.0.0")
                .title("Swagger Petstore");

        final Contact contact = new Contact()
                .name("Swagger API Team")
                .email("foo@bar.baz")
                .url("http://swagger.io");
        info.setContact(contact);

        final AsyncAPI swagger = new AsyncAPI()
                .info(info)
                .addServersItem(new Server().url("http://petstore.swagger.io"))
                //.consumes("application/json")
                //.produces("application/json")
                .schema("Person", personModel);

        final QueryParameter parameter = (QueryParameter) new QueryParameter()
                .name("id")
                .description("a common get parameter")
                .schema(new IntegerSchema());

        final Operation get = new Operation()
                //.produces("application/json")
                .summary("finds pets in the system")
                .description("a longer description")
                //.tag("Pet Operations")
                .operationId("get pet by id")
                .addParametersItem(new Parameter().$ref("#/parameters/Foo"));

        swagger
                .components(new Components().addParameters("Foo", parameter))
                .path("/pets", new PathItem().get(get));

        final String swaggerJson = Json.mapper().writeValueAsString(swagger);
        final AsyncAPI rebuilt = Json.mapper().readValue(swaggerJson, AsyncAPI.class);
        assertEquals(Json.pretty(rebuilt), Json.pretty(swagger));
    }

    @Test
    public void prettyPrintTest() throws IOException {
        final String json = ResourceUtils.loadClassResource(getClass(), "uber.json");
        final AsyncAPI swagger = Json.mapper().readValue(json, AsyncAPI.class);
        final String outputStream = OutputReplacer.OUT.run(new OutputReplacer.Function() {
            @Override
            public void run() {
                Json.prettyPrint(swagger);
            }
        });
        SerializationMatchers.assertEqualsToJson(swagger, outputStream);
    }

    @Test
    public void exceptionsTest() throws IOException {
        final String outputStream1 = OutputReplacer.ERROR.run(new OutputReplacer.Function() {
            @Override
            public void run() {
                Json.pretty(new ThrowHelper());
            }
        });
        assertTrue(outputStream1.contains(ThrowHelper.MESSAGE));

        final String outputStream2 = OutputReplacer.ERROR.run(new OutputReplacer.Function() {
            @Override
            public void run() {
                Json.prettyPrint(new ThrowHelper());
            }
        });
        assertTrue(outputStream2.contains(ThrowHelper.MESSAGE));
    }

    static class ThrowHelper {

        public static final String MESSAGE = "Test exception";

        public String getValue() throws IOException {
            throw new IOException(MESSAGE);
        }

        public void setValue(String value) {

        }
    }
}
