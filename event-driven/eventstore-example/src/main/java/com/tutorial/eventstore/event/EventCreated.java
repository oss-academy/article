package com.tutorial.eventstore.event;

import java.util.UUID;

import static com.tutorial.eventstore.util.JsonUtils.toJsonString;

public record EventCreated(
        String streamId,
        UUID id,
        Long time,
        SerialNumber data
) implements Event<SerialNumber> {

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public String getStreamId() {
        return streamId;
    }

    @Override
    public Long getTime() {
        return time;
    }

    @Override
    public SerialNumber getData() {
        return data;
    }

    @Override
    public String toString() {
        return toJsonString(this);
    }
}
