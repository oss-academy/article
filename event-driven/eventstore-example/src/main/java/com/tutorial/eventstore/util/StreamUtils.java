package com.tutorial.eventstore.util;

import com.eventstore.dbclient.ResolvedEvent;
import com.tutorial.eventstore.event.Event;

import java.util.UUID;

import static com.tutorial.eventstore.util.JsonUtils.toObjectType;

public final class StreamUtils {

    private StreamUtils() {
    }

    public static String generateId(final String streamName) {
        return String.format("%s_%s", streamName, UUID.randomUUID());
    }
    public static Event<Object> extractOriginalEvent(ResolvedEvent e) {
        return (Event<Object>) toObjectType(e.getOriginalEvent().getEventData(), e.getOriginalEvent().getEventType());
    }
}
