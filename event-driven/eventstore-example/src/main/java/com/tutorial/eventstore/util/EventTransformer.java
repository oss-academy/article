package com.tutorial.eventstore.util;

import com.eventstore.dbclient.EventData;
import com.tutorial.eventstore.event.Event;

import java.util.Iterator;
import java.util.List;

import static java.util.Objects.requireNonNull;

public final class EventTransformer {

    private EventTransformer() {
    }

    public static <T, E extends Event<T>> Iterator<EventData> toEventDataIterator(final List<E> events) {
        requireNonNull(events);

        return events.stream()
                .map(event -> EventData
                        .builderAsJson(event.getId(), event.getClass().getTypeName(), event)
                        .build())
                .iterator();
    }
}
