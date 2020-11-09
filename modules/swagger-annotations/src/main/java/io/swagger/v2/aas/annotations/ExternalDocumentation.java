package io.swagger.v2.aas.annotations;

import io.swagger.v2.aas.annotations.headers.Header;
import io.swagger.v2.aas.annotations.media.Schema;
import io.swagger.v2.aas.annotations.tags.Tag;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.METHOD;

/**
 * The annotation may be used at method level or as field of {@link Operation} to add a reference to an external
 * resource for extended documentation of an <a target="_new" href="https://github.com/OAI/OpenAPI-Specification/blob/3.0.1/versions/3.0.1.md#operationObject">Operation (OpenAPI specification)</a>.
 * <p>It may also be used to add external documentation to {@link Tag},
 * {@link Header} or {@link Schema},
 * or as field of {@link AsyncAPIDefinition}.</p>
 *
 * @see <a target="_new" href="https://www.asyncapi.com/docs/specifications/2.0.0#externalDocumentationObject">External Documentation (AsyncAPI specification)</a>
 * @see AsyncAPIDefinition
 **/
@Target({METHOD, TYPE, ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ExternalDocumentation {

    /**
     * A short description of the target documentation.
     *
     * @return the documentation description
     **/
    String description() default "";

    /**
     * The URL for the target documentation. Value must be in the format of a URL.
     *
     * @return the documentation URL
     **/
    String url() default "";

}
