package com.tutorial.eventstore.service;

import com.eventstore.dbclient.WriteResult;
import com.tutorial.eventstore.event.Event;
import com.tutorial.eventstore.stream.EventStream;

import java.util.Optional;

public interface StreamService<T, E extends Event<T>, S extends EventStream<T, E>> {
    Optional<WriteResult> append(S eventStream);

    Optional<S> read(String streamId);

    void delete(String streamId);
}
