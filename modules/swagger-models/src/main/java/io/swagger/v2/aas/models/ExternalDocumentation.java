package io.swagger.v2.aas.models;

import java.util.Objects;

/**
 * ExternalDocumentation
 *
 * @see "https://www.asyncapi.com/docs/specifications/2.0.0#externalDocumentationObject"
 */

public class ExternalDocumentation {
    private String description = null;
    private String url = null;

    /**
     * returns the description property from a ExternalDocumentation instance.
     *
     * @return String description
     **/

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ExternalDocumentation description(String description) {
        this.description = description;
        return this;
    }

    /**
     * returns the url property from a ExternalDocumentation instance.
     *
     * @return String url
     **/

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ExternalDocumentation url(String url) {
        this.url = url;
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
        ExternalDocumentation externalDocumentation = (ExternalDocumentation) o;
        return Objects.equals(this.description, externalDocumentation.description) &&
                Objects.equals(this.url, externalDocumentation.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, url);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ExternalDocumentation {\n");

        sb.append("    description: ").append(toIndentedString(description)).append("\n");
        sb.append("    url: ").append(toIndentedString(url)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

}

