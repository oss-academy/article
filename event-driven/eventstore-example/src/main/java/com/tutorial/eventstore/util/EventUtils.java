package com.tutorial.eventstore.util;

import com.eventstore.dbclient.EventData;
import com.tutorial.eventstore.event.Event;

import java.util.Iterator;
import java.util.List;

public final class EventUtils {

    private EventUtils() {
    }

    public static <T, E extends Event<T>> EventData toJsonEvent(E event) {
        return EventData
                .builderAsJson(event.getId(), event.getClass().getTypeName(), event)
                .build();
    }

    public static <T, E extends Event<T>> Iterator<EventData> toJsonEvent(List<E> events) {
        return events.stream()
                .map(EventUtils::toJsonEvent)
                .iterator();
    }
}
