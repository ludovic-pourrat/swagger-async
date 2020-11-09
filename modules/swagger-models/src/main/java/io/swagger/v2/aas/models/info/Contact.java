package io.swagger.v2.aas.models.info;

import java.util.Objects;

/**
 * Contact
 *
 * @see "https://www.asyncapi.com/docs/specifications/2.0.0#contactObject"
 */

public class Contact {
    private String name = null;
    private String url = null;
    private String email = null;

    /**
     * returns the name property from a Contact instance.
     *
     * @return String name
     **/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Contact name(String name) {
        this.name = name;
        return this;
    }

    /**
     * returns the url property from a Contact instance.
     *
     * @return String url
     **/

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Contact url(String url) {
        this.url = url;
        return this;
    }

    /**
     * returns the email property from a Contact instance.
     *
     * @return String email
     **/

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Contact email(String email) {
        this.email = email;
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
        Contact contact = (Contact) o;
        return Objects.equals(this.name, contact.name) &&
                Objects.equals(this.url, contact.url) &&
                Objects.equals(this.email, contact.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, url, email);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Contact {\n");

        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    url: ").append(toIndentedString(url)).append("\n");
        sb.append("    email: ").append(toIndentedString(email)).append("\n");
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

