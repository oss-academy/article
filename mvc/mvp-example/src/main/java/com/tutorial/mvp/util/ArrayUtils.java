package com.tutorial.mvp.util;

import java.util.stream.Stream;

import static java.lang.String.format;
import static java.util.stream.Collectors.joining;

public final class ArrayUtils {
    private ArrayUtils() {
    }

    public static String convertToString(Object[] arr, String title) {
        return title +
                "\n" +
                "==================================================" +
                "\n" +
                format("%s", Stream.of(arr).map(Object::toString).collect(joining("\n"))) +
                "\n" +
                "==================================================" +
                "\n";
    }
}
