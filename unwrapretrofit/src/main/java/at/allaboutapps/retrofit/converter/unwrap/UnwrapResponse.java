package at.allaboutapps.retrofit.converter.unwrap;

/**
 * Unwrap a {"response": "hi"} to just show what you care about&mdash;"hi"!
 */
public @interface UnwrapResponse {
    /**
     * @return the name of the field with the actual data.
     */
    String value();
}
