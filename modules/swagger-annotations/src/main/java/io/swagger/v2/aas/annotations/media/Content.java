package io.swagger.v2.aas.annotations.media;

import io.swagger.v2.aas.annotations.Parameter;
import io.swagger.v2.aas.annotations.extensions.Extension;
import io.swagger.v2.aas.annotations.parameters.RequestBody;
import io.swagger.v2.aas.annotations.responses.ApiResponse;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The annotation may be used to define the content/media type  of a parameter, request or response, by defining it as
 * field {@link Parameter#content()}, {@link RequestBody#content()} or {@link ApiResponse#content()}.
 * <p>If {@link Content#schema()} is defined, swagger-jaxrs2 reader engine will consider it along with
 * JAX-RS annotations, element type and context as input to resolve the annotated element into an OpenAPI schema
 * definition for such element.</p>
 *
 * @see <a target="_new" href="https://github.com/OAI/OpenAPI-Specification/blob/3.0.1/versions/3.0.1.md#exampleObject">Example (OpenAPI specification)</a>
 * @see Schema
 * @see Parameter
 * @see ApiResponse
 * @see RequestBody
 **/
@Target({ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Content {
    /**
     * The media type that this object applies to.
     *
     * @return the media type value
     **/
    String mediaType() default "";

    /**
     * An array of examples used to show the use of the associated schema.
     *
     * @return the list of examples
     **/
    ExampleObject[] examples() default {};

    /**
     * The schema defining the type used for the content.
     *
     * @return the schema of this media type
     **/
    Schema schema() default @Schema();

    /**
     * The schema of the array that defines the type used for the content.
     *
     * @return the schema of the array
     */
    ArraySchema array() default @ArraySchema();

    /**
     * An array of encodings
     * The key, being the property name, MUST exist in the schema as a property.
     *
     * @return the array of encodings
     */
    Encoding[] encoding() default {};

    /**
     * The list of optional extensions
     *
     * @return an optional array of extensions
     */
    Extension[] extensions() default {};

}
