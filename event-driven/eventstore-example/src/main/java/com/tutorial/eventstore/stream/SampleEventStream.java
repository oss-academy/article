package com.tutorial.eventstore.stream;

import com.tutorial.eventstore.event.Event;

import java.util.List;

public record SampleEventStream(String id, List<Event<Object>> events) implements EventStream<Object, Event<Object>> {

    @Override
    public String getId() {
        return id;
    }

    @Override
    public List<Event<Object>> getEvents() {
        return events;
    }

    @Override
    public SampleStreamStatus getStatus() {
        return null;
    }
}
