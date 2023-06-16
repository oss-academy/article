package com.tutorial.eventstore.util;

import com.tutorial.eventstore.event.Event;
import com.tutorial.eventstore.stream.EventStream;

import java.util.UUID;

import static java.util.Objects.requireNonNull;

public final class StreamUtils {

    private StreamUtils() {
    }

    public static <T, E extends Event<T>, S extends EventStream<T, E>> void isValidStream(S eventStream) {
        requireNonNull(eventStream, "eventStream must not be null");
        requireNonNull(eventStream.getId(), "stream Id must not be null");
        requireNonNull(eventStream.getEvents(), "event list must not be null");
        if (eventStream.getEvents().isEmpty()) {
            throw new IllegalArgumentException("event list must not be empty");
        }
    }

    public static String generateStreamId(String name, UUID relativeId) {
        return String.format("%s_%s", name, relativeId);
    }

}
