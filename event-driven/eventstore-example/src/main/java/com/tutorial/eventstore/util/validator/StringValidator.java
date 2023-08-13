package com.tutorial.eventstore.util.validator;

import static java.util.Objects.requireNonNull;

public final class StringValidator {

    private StringValidator() {
    }

    public static void shouldNotBeNullOrEmpty(String str, String message) {
        requireNonNull(str, message);
        if (str.isEmpty()) {
            throw new IllegalArgumentException(message );
        }
    }

}
