package com.tutorial.eventstore.event;

import java.util.UUID;

import static com.tutorial.eventstore.util.JsonUtils.toJsonString;

public record SampleClosed(UUID id, String name) implements Event {

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return toJsonString(this);
    }
}
