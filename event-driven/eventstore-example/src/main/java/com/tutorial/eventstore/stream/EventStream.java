package com.tutorial.eventstore.stream;

import com.tutorial.eventstore.event.Event;

import java.util.List;

public interface EventStream<T, E extends Event<T>> {

    String getId();

    List<E> getEvents();

    SampleStreamStatus getStatus();
}
