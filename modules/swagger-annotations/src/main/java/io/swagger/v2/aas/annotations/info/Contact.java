package io.swagger.v2.aas.annotations.info;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.swagger.v2.aas.annotations.AsyncAPIDefinition;

/**
 * The annotation may be used in {@link Info#contact()} to define a contact for the AsyncAPI spec.
 *
 * @see <a target="_new" href="https://www.asyncapi.com/docs/specifications/2.0.0#contactObject">Contact (AsyncAPI specification)</a>
 * @see AsyncAPIDefinition
 * @see Info
 **/
@Target({})
@Retention(RetentionPolicy.RUNTIME)
public @interface Contact {
    /**
     * The identifying name of the contact person/organization.
     *
     * @return the name of the contact
     **/
    String name() default "";

    /**
     * The URL pointing to the contact information. Must be in the format of a URL.
     *
     * @return the URL of the contact
     **/
    String url() default "";

    /**
     * The email address of the contact person/organization. Must be in the format of an email address.
     *
     * @return the email address of the contact
     **/
    String email() default "";

}
