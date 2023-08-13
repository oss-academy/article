package com.tutorial.eventstore.stream;

import com.tutorial.eventstore.event.Event;

import java.util.List;

import static java.util.Comparator.comparingLong;

public record SampleEventStream<T>(String id, List<Event<T>> events) implements EventStream<T, Event<T>> {

    @Override
    public String getId() {
        return id;
    }

    @Override
    public List<Event<T>> getEvents() {
        if (events == null || events.isEmpty()){
            return events;
        }

        return events.stream()
                .sorted(comparingLong(Event::getTime))
                .toList();
    }

    @Override
    public SampleStreamStatus getStatus() {
        return null;
    }
}
