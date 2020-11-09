package io.swagger.v2.aas.models.media;

import java.util.Objects;

/**
 * ArraySchema
 */

public class ArraySchema extends Schema<Object> {
    private Schema<?> items = null;

    public ArraySchema() {
        super("array", null);
    }

    @Override
    public ArraySchema type(String type) {
        super.setType(type);
        return this;
    }

    /**
     * returns the items property from a ArraySchema instance.
     *
     * @return Schema items
     **/

    public Schema<?> getItems() {
        return items;
    }

    public void setItems(Schema<?> items) {
        this.items = items;
    }

    public ArraySchema items(Schema<?> items) {
        this.items = items;
        return this;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ArraySchema arraySchema = (ArraySchema) o;
        return Objects.equals(this.items, arraySchema.items) &&
                super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(items, super.hashCode());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ArraySchema {\n");
        sb.append("    ").append(toIndentedString(super.toString())).append("\n");
        sb.append("    items: ").append(toIndentedString(items)).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
