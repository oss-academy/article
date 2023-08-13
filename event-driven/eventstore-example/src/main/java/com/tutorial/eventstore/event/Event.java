package com.tutorial.eventstore.event;

import java.util.UUID;

public interface Event<T> {

    UUID getId();

    String getStreamId();

    Long getTime();

    T getData();

}
