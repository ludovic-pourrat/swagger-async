package io.swagger.v2.aas.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.swagger.v2.aas.models.info.Info;
import io.swagger.v2.aas.models.media.Schema;
import io.swagger.v2.aas.models.security.SecurityRequirement;
import io.swagger.v2.aas.models.security.SecurityScheme;
import io.swagger.v2.aas.models.servers.Server;
import io.swagger.v2.aas.models.tags.Tag;

/**
 * OpenAPI
 *
 * @see "https://www.asyncapi.com/docs/specifications/2.0.0"
 */

public class AsyncAPI {
    
    private String asyncapi = "2.0.0";

    private Info info = null;

    private ExternalDocumentation externalDocs = null;

    private List<Server> servers = null;

    private List<SecurityRequirement> security = null;

    private List<Tag> tags = null;

    private Paths paths = null;

    private Components components = null;

    private java.util.Map<String, Object> extensions = null;

    /**
     * returns the openapi property from a AsyncAPI instance.
     *
     * @return String openapi
     **/

    public String getAsyncapi() {
        return asyncapi;
    }

    public void setAsyncapi(String asyncapi) {
        this.asyncapi = asyncapi;
    }

    public AsyncAPI asyncapi(String asyncapi) {
        this.asyncapi = asyncapi;
        return this;
    }

    /**
     * returns the info property from a AsyncAPI instance.
     *
     * @return Info info
     **/

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public AsyncAPI info(Info info) {
        this.info = info;
        return this;
    }

    /**
     * returns the externalDocs property from a OpenAPI instance.
     *
     * @return ExternalDocumentation externalDocs
     **/

    public ExternalDocumentation getExternalDocs() {
        return externalDocs;
    }

    public void setExternalDocs(ExternalDocumentation externalDocs) {
        this.externalDocs = externalDocs;
    }

    public AsyncAPI externalDocs(ExternalDocumentation externalDocs) {
        this.externalDocs = externalDocs;
        return this;
    }

    /**
     * Servers defined in the API
     *
     * @return List&lt;Server&gt; servers
     **/

    public List<Server> getServers() {
        return servers;
    }

    public void setServers(List<Server> servers) {
        this.servers = servers;
    }

    public AsyncAPI servers(List<Server> servers) {
        this.servers = servers;
        return this;
    }

    public AsyncAPI addServersItem(Server serversItem) {
        if (this.servers == null) {
            this.servers = new ArrayList<>();
        }
        this.servers.add(serversItem);
        return this;
    }

    /**
     * returns the security property from a OpenAPI instance.
     *
     * @return List&lt;SecurityRequirement&gt; security
     **/

    public List<SecurityRequirement> getSecurity() {
        return security;
    }

    public void setSecurity(List<SecurityRequirement> security) {
        this.security = security;
    }

    public AsyncAPI security(List<SecurityRequirement> security) {
        this.security = security;
        return this;
    }

    public AsyncAPI addSecurityItem(SecurityRequirement securityItem) {
        if (this.security == null) {
            this.security = new ArrayList<>();
        }
        this.security.add(securityItem);
        return this;
    }

    /**
     * returns the tags property from a OpenAPI instance.
     *
     * @return List&lt;Tag&gt; tags
     **/

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public AsyncAPI tags(List<Tag> tags) {
        this.tags = tags;
        return this;
    }

    public AsyncAPI addTagsItem(Tag tagsItem) {
        if (this.tags == null) {
            this.tags = new ArrayList<>();
        }
        this.tags.add(tagsItem);
        return this;
    }

    /**
     * returns the paths property from a OpenAPI instance.
     *
     * @return Paths paths
     **/

    public Paths getPaths() {
        return paths;
    }

    public void setPaths(Paths paths) {
        this.paths = paths;
    }

    public AsyncAPI paths(Paths paths) {
        this.paths = paths;
        return this;
    }

    /**
     * returns the components property from a OpenAPI instance.
     *
     * @return Components components
     **/

    public Components getComponents() {
        return components;
    }

    public void setComponents(Components components) {
        this.components = components;
    }

    public AsyncAPI components(Components components) {
        this.components = components;
        return this;
    }

    /*
     * helpers
     */

    public AsyncAPI path(String name, PathItem path) {
        if (this.paths == null) {
            this.paths = new Paths();
        }

        this.paths.addPathItem(name, path);
        return this;
    }

    public AsyncAPI schema(String name, Schema schema) {
        if (components == null) {
            this.components = new Components();
        }
        components.addSchemas(name, schema);
        return this;
    }

    public AsyncAPI schemaRequirement(String name, SecurityScheme securityScheme) {
        if (components == null) {
            this.components = new Components();
        }
        components.addSecuritySchemes(name, securityScheme);
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
        AsyncAPI asyncAPI = (AsyncAPI) o;
        return Objects.equals(this.asyncapi, asyncAPI.asyncapi) &&
                Objects.equals(this.info, asyncAPI.info) &&
                Objects.equals(this.externalDocs, asyncAPI.externalDocs) &&
                Objects.equals(this.servers, asyncAPI.servers) &&
                Objects.equals(this.security, asyncAPI.security) &&
                Objects.equals(this.tags, asyncAPI.tags) &&
                Objects.equals(this.paths, asyncAPI.paths) &&
                Objects.equals(this.components, asyncAPI.components) &&
                Objects.equals(this.extensions, asyncAPI.extensions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(asyncapi, info, externalDocs, servers, security, tags, paths, components, extensions);
    }

    public java.util.Map<String, Object> getExtensions() {
        return extensions;
    }

    public void addExtension(String name, Object value) {
        if (name == null || name.isEmpty() || !name.startsWith("x-")) {
            return;
        }
        if (this.extensions == null) {
            this.extensions = new java.util.LinkedHashMap<>();
        }
        this.extensions.put(name, value);
    }

    public void setExtensions(java.util.Map<String, Object> extensions) {
        this.extensions = extensions;
    }

    public AsyncAPI extensions(java.util.Map<String, Object> extensions) {
        this.extensions = extensions;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class AsyncAPI {\n");

        sb.append("    asyncapi: ").append(toIndentedString(asyncapi)).append("\n");
        sb.append("    info: ").append(toIndentedString(info)).append("\n");
        sb.append("    externalDocs: ").append(toIndentedString(externalDocs)).append("\n");
        sb.append("    servers: ").append(toIndentedString(servers)).append("\n");
        sb.append("    security: ").append(toIndentedString(security)).append("\n");
        sb.append("    tags: ").append(toIndentedString(tags)).append("\n");
        sb.append("    paths: ").append(toIndentedString(paths)).append("\n");
        sb.append("    components: ").append(toIndentedString(components)).append("\n");
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

