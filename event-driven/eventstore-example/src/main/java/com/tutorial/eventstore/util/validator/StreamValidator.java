package com.tutorial.eventstore.util.validator;

import com.tutorial.eventstore.event.Event;
import com.tutorial.eventstore.stream.EventStream;

import static com.tutorial.eventstore.util.validator.StringValidator.shouldNotBeNullOrEmpty;
import static java.util.Objects.requireNonNull;

public final class StreamValidator {

    private StreamValidator() {
    }

    public static <T, E extends Event<T>, S extends EventStream<T, E>> void isValidStream(final S eventStream) {
        requireNonNull(eventStream, "eventStream should not be null");
        shouldNotBeNullOrEmpty(eventStream.getId(), "stream Id should not be blank");
        requireNonNull(eventStream.getEvents(), "event list should not be null");
        if (eventStream.getEvents().isEmpty()) {
            throw new IllegalArgumentException("event list should not be empty");
        }
    }

}
