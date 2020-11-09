package io.swagger.v2.async.converting;

import io.swagger.v2.async.converter.ModelConverters;
import io.swagger.v2.async.matchers.SerializationMatchers;
import io.swagger.v2.async.oas.models.JCovariantGetter;
import io.swagger.v2.aas.models.media.Schema;
import org.testng.annotations.Test;

import java.util.Map;

import static org.testng.Assert.assertEquals;

public class CovariantGetterTest {
    @Test(description = "it should read a getter with covariant return type")
    public void testCovariantGetter() {
        final Map<String, Schema> models = ModelConverters.getInstance().read(JCovariantGetter.Sub.class);
        assertEquals(models.size(), 1);
        final String json = "{" +
                "   \"Sub\":{" +
                "      \"type\":\"object\"," +
                "      \"properties\":{" +
                "         \"myProperty\":{" +
                "            \"type\":\"integer\"," +
                "            \"format\":\"int32\"" +
                "         }," +
                "         \"myOtherProperty\":{" +
                "            \"type\":\"integer\"," +
                "            \"format\":\"int32\"" +
                "         }" +
                "      }" +
                "   }" +
                "}";
        SerializationMatchers.assertEqualsToJson(models, json);
    }
}
