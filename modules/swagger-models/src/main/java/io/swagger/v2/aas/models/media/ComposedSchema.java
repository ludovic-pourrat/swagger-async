package io.swagger.v2.aas.models.media;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * ComposedSchema
 */

public class ComposedSchema extends Schema<Object> {
    private List<Schema> allOf = null;
    private List<Schema> anyOf = null;
    private List<Schema> oneOf = null;

    /**
     * returns the allOf property from a ComposedSchema instance.
     *
     * @return List&lt;Schema&gt; allOf
     **/

    public List<Schema> getAllOf() {
        return allOf;
    }

    public void setAllOf(List<Schema> allOf) {
        this.allOf = allOf;
    }

    public ComposedSchema allOf(List<Schema> allOf) {
        this.allOf = allOf;
        return this;
    }

    public ComposedSchema addAllOfItem(Schema allOfItem) {
        if (this.allOf == null) {
            this.allOf = new ArrayList<>();
        }
        this.allOf.add(allOfItem);
        return this;
    }

    /**
     * returns the anyOf property from a ComposedSchema instance.
     *
     * @return List&lt;Schema&gt; anyOf
     **/

    public List<Schema> getAnyOf() {
        return anyOf;
    }

    public void setAnyOf(List<Schema> anyOf) {
        this.anyOf = anyOf;
    }

    public ComposedSchema anyOf(List<Schema> anyOf) {
        this.anyOf = anyOf;
        return this;
    }

    public ComposedSchema addAnyOfItem(Schema anyOfItem) {
        if (this.anyOf == null) {
            this.anyOf = new ArrayList<>();
        }
        this.anyOf.add(anyOfItem);
        return this;
    }

    /**
     * returns the oneOf property from a ComposedSchema instance.
     *
     * @return List&lt;Schema&gt; oneOf
     **/

    public List<Schema> getOneOf() {
        return oneOf;
    }

    public void setOneOf(List<Schema> oneOf) {
        this.oneOf = oneOf;
    }

    public ComposedSchema oneOf(List<Schema> oneOf) {
        this.oneOf = oneOf;
        return this;
    }

    public ComposedSchema addOneOfItem(Schema oneOfItem) {
        if (this.oneOf == null) {
            this.oneOf = new ArrayList<>();
        }
        this.oneOf.add(oneOfItem);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ComposedSchema allOfSchema = (ComposedSchema) o;
        return Objects.equals(this.allOf, allOfSchema.allOf) &&
                Objects.equals(this.anyOf, allOfSchema.anyOf) &&
                Objects.equals(this.oneOf, allOfSchema.oneOf) &&
                super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(allOf, anyOf, oneOf, super.hashCode());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ComposedSchema {\n");
        sb.append("    ").append(toIndentedString(super.toString())).append("\n");
        sb.append("    allOf: ").append(toIndentedString(allOf)).append("\n");
        sb.append("    anyOf: ").append(toIndentedString(anyOf)).append("\n");
        sb.append("    oneOf: ").append(toIndentedString(oneOf)).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
