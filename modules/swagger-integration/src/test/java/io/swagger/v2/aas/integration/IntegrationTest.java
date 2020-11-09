package io.swagger.v2.aas.integration;

import static org.testng.Assert.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import io.swagger.v2.aas.integration.api.AsyncAPIConfiguration;
import io.swagger.v2.aas.integration.api.AsyncApiContext;
import io.swagger.v2.aas.models.AsyncAPI;
import io.swagger.v2.aas.models.info.Info;
import org.testng.annotations.Test;

public class IntegrationTest {

    // TODO after implementation of generic reader and possibly generic scanner if we reintroduce "api" or similar annotation
    private final Set expectedKeys = new HashSet<String>(Arrays.asList("/packageA", "/packageB"));

    @Test(description = "initialize a context and read")
    public void shouldInitialize() throws Exception {

        AsyncAPIConfiguration config = new SwaggerConfiguration()
                .resourcePackages(Stream.of("com.my.project.resources", "org.my.project.resources").collect(Collectors.toSet()))
                .asyncAPI(new AsyncAPI().info(new Info().description("TEST INFO DESC")));

        AsyncApiContext ctx = new GenericAsyncApiContext()
                .openApiConfiguration(config)
                //.openApiReader()
                //.openApiReader()
                .init();
        AsyncAPI asyncAPI = ctx.read();

        assertNotNull(asyncAPI);
    }

}
