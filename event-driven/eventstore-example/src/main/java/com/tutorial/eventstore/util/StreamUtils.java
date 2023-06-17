package com.tutorial.eventstore.util;

import java.util.UUID;

public final class StreamUtils {

    private StreamUtils() {
    }

    public static String generateId(final String streamName) {
        return String.format("%s_%s", streamName, UUID.randomUUID());
    }

}
