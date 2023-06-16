package com.tutorial.eventstore.event;

import java.util.UUID;

import static com.tutorial.eventstore.util.JsonUtils.toJsonString;

public record SampleProcessed(String streamId, UUID id, Object data) implements Event<Object> {

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public String getStreamId() {
        return streamId;
    }

    @Override
    public Object getData() {
        return data;
    }

    @Override
    public String toString() {
        return toJsonString(this);
    }
}
