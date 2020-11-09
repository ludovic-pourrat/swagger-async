package io.swagger.v2.async.serialization;

import io.swagger.v2.async.matchers.SerializationMatchers;
import io.swagger.v2.aas.models.examples.Example;
import io.swagger.v2.aas.models.media.Content;
import io.swagger.v2.aas.models.media.MediaType;
import io.swagger.v2.aas.models.responses.ApiResponse;
import org.testng.annotations.Test;

import java.io.IOException;

public class ResponseExamplesTest {
    @Test(description = "it should create a response")
    public void createResponse() throws IOException {
        final ApiResponse response = new ApiResponse()
                .content(new Content()
                        .addMediaType("application/json", new MediaType()
                                .addExamples("test", new Example().value("{\"name\":\"Fred\",\"id\":123456\"}"))));

        final String json = "{\n" +
                "  \"content\" : {\n" +
                "    \"application/json\" : {\n" +
                "      \"examples\" : {\n" +
                "        \"test\" : {\n" +
                "          \"value\" : \"{\\\"name\\\":\\\"Fred\\\",\\\"id\\\":123456\\\"}\"\n" +
                "        }\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}";
        SerializationMatchers.assertEqualsToJson(response, json);
    }
}
