package io.swagger.v2.aas.annotations.servers;

import io.swagger.v2.aas.annotations.AsyncAPIDefinition;
import io.swagger.v2.aas.annotations.Operation;
import io.swagger.v2.aas.annotations.extensions.Extension;

import java.lang.annotation.Inherited;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.METHOD;

/**
 * The annotation may be applied at class or method level, or in {@link Operation#servers()} to define servers for the
 * single operation (when applied at method level) or for all operations of a class (when applied at class level).
 * <p>It can also be used in {@link AsyncAPIDefinition#servers()} to define spec level servers.</p>
 *
 * @see <a target="_new" href="https://www.asyncapi.com/docs/specifications/2.0.0#serverObject">Server (AsyncAPI specification)</a>
 * @see AsyncAPIDefinition
 * @see Operation
 **/
@Target({METHOD, TYPE, ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(Servers.class)
@Inherited
public @interface Server {

    /**
     * Required. A URL to the target host.
     * This URL supports Server Variables and may be relative, to indicate that the host location is relative to the location where the
     * OpenAPI definition is being served. Variable substitutions will be made when a variable is named in {brackets}.
     *
     * @return String url
     **/
    String url() default "";

    /**
     * Required. The protocol this URL supports for connection.
     * Supported protocol include, but are not limited to: amqp, amqps, http, https, jms, kafka, kafka-secure, mqtt, secure-mqtt, stomp, stomps, ws, wss.
     *
     * @return String protocol
     **/
    String protocol() default "";

    /**
     * Required. TThe version of the protocol used for connection.
     * For instance: AMQP 0.9.1, HTTP 2.0, Kafka 1.0.0, etc.
     *
     * @return String protocolVersion
     **/
    String protocolVersion() default "";

    /**
     * An optional string describing the host designated by the URL. CommonMark syntax MAY be used for rich text representation.
     *
     * @return String description
     **/
    String description() default "";

    /**
     * An array of variables used for substitution in the server's URL template.
     *
     * @return array of ServerVariables
     **/
    ServerVariable[] variables() default {};

}
