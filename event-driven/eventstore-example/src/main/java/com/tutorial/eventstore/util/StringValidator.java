package com.tutorial.eventstore.util;

import static java.util.Objects.requireNonNull;

public final class StringValidator {

    private StringValidator() {
    }

    public static void shouldNotBeNullOrEmpty(String str, String subject) {
        requireNonNull(str, subject + " should not be null");
        if (str.isEmpty()) {
            throw new IllegalArgumentException(subject + " should not be empty");
        }
    }

}
