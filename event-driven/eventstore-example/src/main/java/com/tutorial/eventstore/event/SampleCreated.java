package com.tutorial.eventstore.event;

import java.util.UUID;

import static com.tutorial.eventstore.util.JsonUtils.toJsonString;

public record SampleCreated(UUID id, String name) implements Event {

    @Override
    public String toString() {
        return toJsonString(this);
    }

    @Override
    public UUID getId() {
        return id;
    }
}
