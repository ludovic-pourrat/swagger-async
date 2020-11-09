package io.swagger.v2.aas.models.servers;

import java.util.Objects;

/**
 * Server
 *
 * @see "https://www.asyncapi.com/docs/specifications/2.0.0#serverObject"
 */

public class Server {

    private String url = null;
    private String description = null;
    private String protocol = null;
    private String protocolVersion = null;
    private ServerVariables variables = null;

    /**
     * returns the url property from a Server instance.
     *
     * @return String url
     **/

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Server url(String url) {
        this.url = url;
        return this;
    }

    /**
     * returns the protocol property from a Server instance.
     *
     * @return String protocol
     **/

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public Server protocol(String protocol) {
        this.protocol = protocol;
        return this;
    }

    /**
     * returns the url protocolVersion from a Server instance.
     *
     * @return String url
     **/

    public String getProtocolVersion() {
        return protocolVersion;
    }

    public void setProtocolVersion(String protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    public Server protocolVersion(String protocolVersion) {
        this.protocolVersion = protocolVersion;
        return this;
    }

    /**
     * returns the description property from a Server instance.
     *
     * @return String description
     **/

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Server description(String description) {
        this.description = description;
        return this;
    }

    /**
     * returns the variables property from a Server instance.
     *
     * @return ServerVariables variables
     **/

    public ServerVariables getVariables() {
        return variables;
    }

    public void setVariables(ServerVariables variables) {
        this.variables = variables;
    }

    public Server variables(ServerVariables variables) {
        this.variables = variables;
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
        Server server = (Server) o;
        return Objects.equals(this.url, server.url) &&
                Objects.equals(this.description, server.description) &&
                Objects.equals(this.protocol, server.protocol) &&
                Objects.equals(this.protocolVersion, server.protocolVersion) &&
                Objects.equals(this.variables, server.variables);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, description, variables, protocol, protocolVersion);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Server {\n");

        sb.append("    url: ").append(toIndentedString(url)).append("\n");
        sb.append("    description: ").append(toIndentedString(description)).append("\n");
        sb.append("    protocol: ").append(toIndentedString(protocol)).append("\n");
        sb.append("    protocolVersion: ").append(toIndentedString(protocolVersion)).append("\n");
        sb.append("    variables: ").append(toIndentedString(variables)).append("\n");
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

