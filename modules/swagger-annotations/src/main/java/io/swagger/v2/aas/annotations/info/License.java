package io.swagger.v2.aas.annotations.info;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.swagger.v2.aas.annotations.AsyncAPIDefinition;

/**
 * The annotation may be used in {@link Info#license()} to define a license for the AsyncAPI spec.
 *
 * @see <a target="_new" href="https://www.asyncapi.com/docs/specifications/2.0.0#licenseObject">License (AsyncAPI specification)</a>
 * @see AsyncAPIDefinition
 * @see Info
 **/
@Target({})
@Retention(RetentionPolicy.RUNTIME)
public @interface License {
    /**
     * The license name used for the API.
     *
     * @return the name of the license
     **/
    String name() default "";

    /**
     * A URL to the license used for the API. MUST be in the format of a URL.
     *
     * @return the URL of the license
     **/
    String url() default "";

}
