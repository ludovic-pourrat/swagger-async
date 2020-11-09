package io.swagger.v2.async.converting.override;

import io.swagger.v2.async.converting.override.resources.MyCustomClass;
import io.swagger.v2.async.converter.ModelConverters;
import io.swagger.v2.async.matchers.SerializationMatchers;
import io.swagger.v2.async.util.Json;
import io.swagger.v2.aas.models.media.Schema;
import org.testng.annotations.Test;

import java.util.Map;

public class ModelPropertyOverrideTest {
    @Test
    public void overrideTest() throws Exception {
        ModelConverters.getInstance().addConverter(new SamplePropertyConverter());
        final Map<String, Schema> model = ModelConverters.getInstance().read(MyPojo.class);
        final String expected = "{" +
                "  \"MyPojo\" : {" +
                "    \"type\" : \"object\"," +
                "    \"properties\" : {" +
                "      \"id\" : {" +
                "        \"type\" : \"string\"" +
                "      }," +
                "      \"myCustomClass\" : {" +
                "        \"type\" : \"string\"," +
                "        \"format\" : \"date-time\"" +
                "      }" +
                "    }" +
                "  }" +
                "}";
        SerializationMatchers.assertEqualsToJson(model, expected);
    }

    @Test
    public void extendedOverrideTest() throws Exception {
        ModelConverters.getInstance().addConverter(new SamplePropertyExtendedConverter(Json.mapper()));
        final Map<String, Schema> model = ModelConverters.getInstance().read(MyPojo.class);
        final String expected = "{" +
                "  \"MyPojo\" : {" +
                "    \"type\" : \"object\"," +
                "    \"properties\" : {" +
                "      \"id\" : {" +
                "        \"type\" : \"string\"" +
                "      }," +
                "      \"myCustomClass\" : {" +
                "        \"type\" : \"string\"," +
                "        \"format\" : \"date-time\"," +
                "        \"description\" : \"instead of modeling this class in the documentation, we will model a string\"" +
                "      }" +
                "    }" +
                "  }" +
                "}";
        SerializationMatchers.assertEqualsToJson(model, expected);
    }

    public static class MyPojo {
        public String getId() {
            return "";
        }

        public void setId(String id) {
        }

        @io.swagger.v2.aas.annotations.media.Schema(required = false, description = "instead of modeling this class in the documentation, we will model a string")
        public MyCustomClass getMyCustomClass() {
            return null;
        }

        public void setMyCustomClass(MyCustomClass myCustomClass) {
        }
    }
}
