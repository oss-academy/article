package com.tutorial.eventstore.util;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import static com.tutorial.eventstore.util.validator.StringValidator.shouldNotBeNullOrEmpty;
import static java.util.Objects.requireNonNull;

public final class CollectionUtils {
    private CollectionUtils() {
    }

    public static String joinEntry(
            final Map<String, String> map,
            final String entrySplitter,
            final String entryDelimiter) {

        requireNonNull(map);
        shouldNotBeNullOrEmpty(entrySplitter, "entrySplitter should not be blank");
        shouldNotBeNullOrEmpty(entryDelimiter, "entryDelimiter should not be blank");

        return map.entrySet()
                .stream()
                .map(entry -> entry.getKey() + entrySplitter + entry.getValue())
                .collect(Collectors.joining(entryDelimiter));
    }

    public static String joinArray(final Object[] arr, final String delimiter) {
        requireNonNull(arr);
        shouldNotBeNullOrEmpty(delimiter, "delimiter should not be blank");

        return Arrays.stream(arr)
                .map(Object::toString)
                .collect(Collectors.joining(delimiter));
    }
}
