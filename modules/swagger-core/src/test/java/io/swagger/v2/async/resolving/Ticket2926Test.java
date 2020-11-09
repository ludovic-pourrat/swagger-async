package io.swagger.v2.async.resolving;

import io.swagger.v2.aas.models.AsyncAPI;
import io.swagger.v2.async.matchers.SerializationMatchers;
import io.swagger.v2.async.util.Yaml;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

@Ignore
public class Ticket2926Test extends SwaggerTestBase {

    @Test
    public void testExtensionsInMapDeserializeAndSerialize() throws Exception {
        String yaml = "openapi: 3.0.1\n" +
                "info:\n" +
                "  title: My title\n" +
                "  description: API under test\n" +
                "  version: 1.0.7\n" +
                "  x-info: test\n" +
                "servers:\n" +
                "- url: http://localhost:9999/api\n" +
                "  x-server: test\n" +
                "  description: desc\n" +
                "  variables: \n" +
                "    serVar: \n" +
                "      description: desc\n" +
                "      x-serverVariable: test\n" +
                "paths:\n" +
                "  /foo/bar:\n" +
                "    get:\n" +
                "      callbacks:\n" +
                "        /foo/bar:\n" +
                "          get:\n" +
                "            description: getoperation\n" +
                "          x-callback: test\n" +
                "      responses:\n" +
                "        default:\n" +
                "          description: it works!\n" +
                "          content:\n" +
                "            application/json:\n" +
                "              schema:\n" +
                "                title: inline_response_200\n" +
                "                type: object\n" +
                "                properties:\n" +
                "                  name:\n" +
                "                    type: string\n" +
                "              x-mediatype: test\n" +
                "          x-response: test\n" +
                "        x-responses: test\n" +
                "        x-responses-object: \n" +
                "          aaa: bbb\n" +
                "        x-responses-array: \n" +
                "          - aaa\n" +
                "          - bbb\n" +
                "      x-operation: test\n" +
                "    x-pathitem: test\n" +
                "  x-paths: test\n" +
                "x-openapi-object: \n" +
                "  aaa: bbb\n" +
                "x-openapi-array: \n" +
                "  - aaa\n" +
                "  - bbb\n" +
                "x-openapi: test";

        AsyncAPI aa = Yaml.mapper().readValue(yaml, AsyncAPI.class);
        SerializationMatchers.assertEqualsToYaml(aa, yaml);

    }

}
