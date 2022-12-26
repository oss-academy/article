package com.tutorial.article.util;

import java.lang.reflect.Array;
import java.util.stream.Stream;

import static java.lang.String.format;
import static java.lang.System.arraycopy;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.joining;

public final class ArrayUtils {
    private ArrayUtils() {
    }

    public static <T> T[] addToEnd(T[] arr, T element, Class<T> c) {
        requireNonNull(arr);
        requireNonNull(element);
        requireNonNull(c);

        T[] newArray = increaseLengthFromEndSide(arr, 1, c);
        newArray[newArray.length - 1] = element;

        return newArray;
    }

    public static <T> T[] increaseLengthFromEndSide(T[] arr, int length, Class<T> c) {
        requireNonNull(arr);
        if (length < 0) {
            throw new RuntimeException("length should not be less than zero");
        }
        requireNonNull(c);

        T[] newArray = (T[]) Array.newInstance(c, arr.length + length);

        arraycopy(arr, 0, newArray, 0, arr.length);
        return newArray;
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
