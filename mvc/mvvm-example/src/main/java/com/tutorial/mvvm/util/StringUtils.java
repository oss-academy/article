package com.tutorial.mvvm.util;

import static java.util.Objects.nonNull;

public final class StringUtils {

    private StringUtils() {
    }

    public static boolean isEmpty(String str) {
        return nonNull(str) && str.isEmpty();
    }

    public static String requireNonEmpty(String str) {
        if (isEmpty(str)){
            throw new RuntimeException("string is empty");
        }

        return str;
    }
}
