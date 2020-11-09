package io.swagger.v2.async.resolving.resources;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import io.swagger.v2.aas.annotations.media.Schema;

@Schema(name = "TestObjectTicket2620Subtypes", description = "TestObject description", oneOf = {
        TestObjectTicket2620Subtypes.ChildTestObject.class,
        TestObjectTicket2620Subtypes.Child2TestObject.class
})
@JsonSubTypes({ @JsonSubTypes.Type(value = TestObjectTicket2620Subtypes.ChildTestObject.class),
                @JsonSubTypes.Type(value = TestObjectTicket2620Subtypes.Child2TestObject.class)})
public class TestObjectTicket2620Subtypes {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    class ChildTestObject extends TestObjectTicket2620Subtypes{
        private String childName;

        public String getChildName() {
            return childName;
        }

        public void setChildName(String childName) {
            this.childName = childName;
        }
    }

    class Child2TestObject extends TestObjectTicket2620Subtypes{
        private String childName;

        public String getChildName() {
            return childName;
        }

        public void setChildName(String childName) {
            this.childName = childName;
        }
    }
}


