package io.swagger.v2.aas.annotations;

import static java.lang.annotation.ElementType.*;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.swagger.v2.aas.annotations.info.Info;
import io.swagger.v2.aas.annotations.security.SecurityRequirement;
import io.swagger.v2.aas.annotations.servers.Server;
import io.swagger.v2.aas.annotations.tags.Tag;

/**
 * The annotation that may be used to populate AsyncAPI Objects
 * If more than one class is annotated with {@link AsyncAPIDefinition}, with the same fields defined, behaviour is inconsistent.
 *
 * @see <a target="_new" href="https://www.asyncapi.com/docs/specifications/2.0.0">AsyncAPI (AsyncAPI specification)</a>
 */
@Target({ TYPE, PACKAGE, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface AsyncAPIDefinition {
    
    /**
     * Provides metadata about the API. The metadata MAY be used by tooling as required.
     *
     * @return the metadata about this API
     */
    Info info() default @Info;

    /**
     * A list of tags used by the specification with additional metadata.
     * The order of the tags can be used to reflect on their order by the parsing tools.
     *
     * @return the tags used by the specification with any additional metadata
     */
    Tag[] tags() default {};

    /**
     * An array of Server Objects, which provide connectivity information to a target server.
     * If the servers property is not provided, or is an empty array, the default value would be a Server Object with a url value of /.
     *
     * @return the servers of this API
     */
    Server[] servers() default {};

    /**
     * A declaration of which security mechanisms can be used across the API.
     *
     * @return the array of servers used for this API
     */
    SecurityRequirement[] security() default {};

    /**
     * Any additional external documentation for the API
     *
     * @return the external documentation for this API.
     */
    ExternalDocumentation externalDocs() default @ExternalDocumentation;

}
