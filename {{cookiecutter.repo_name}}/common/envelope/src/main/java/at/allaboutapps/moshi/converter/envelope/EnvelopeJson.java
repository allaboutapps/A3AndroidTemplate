package at.allaboutapps.moshi.converter.envelope;

import com.squareup.moshi.JsonQualifier;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Envelope a field within an object.
 *
 * <pre>{@code
 *     @EnvelopeJson("data")
 *     String message;
 * }</pre>
 * <p>
 * would be represented as <code>{"message":{"data":&lt;value>}}</code>
 */
@Documented
@JsonQualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
public @interface EnvelopeJson {
    String value();
}
