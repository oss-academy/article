package com.tutorial.article.util;

import static java.util.Objects.nonNull;

public final class StringUtils {

    private StringUtils() {
    }

    public static boolean isEmpty(String str) {
        return nonNull(str) && str.isEmpty();
    }
}
