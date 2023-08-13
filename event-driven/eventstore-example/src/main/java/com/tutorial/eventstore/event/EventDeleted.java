package com.tutorial.eventstore.event;

import java.util.UUID;

import static com.tutorial.eventstore.util.JsonUtils.toJsonString;

public record EventDeleted(
        String streamId,
        UUID id,
        Long time,
        Item data
) implements Event<Item> {

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
    public Item getData() {
        return data;
    }

    @Override
    public String toString() {
        return toJsonString(this);
    }
}
