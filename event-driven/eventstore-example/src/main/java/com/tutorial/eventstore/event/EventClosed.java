package com.tutorial.eventstore.event;

import java.util.UUID;

import static com.tutorial.eventstore.util.JsonUtils.toJsonString;

public record EventClosed(
        String streamId,
        UUID id,
        Long time,
        Reason data
) implements Event<Reason> {

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
    public Reason getData() {
        return data;
    }

    @Override
    public String toString() {
        return toJsonString(this);
    }
}
