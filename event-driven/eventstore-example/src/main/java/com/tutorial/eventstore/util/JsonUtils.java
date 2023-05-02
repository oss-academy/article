package com.tutorial.eventstore.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tutorial.eventstore.event.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static java.util.Objects.requireNonNull;

public final class JsonUtils {

    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class.getSimpleName());

    private static final ObjectMapper mapper = new ObjectMapper();

    private JsonUtils() {
    }

    public static String toJsonString(final Object o) {
        requireNonNull(o);

        try {
            return mapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());
            return "";
        }
    }

    public static <T extends Event> Object toType(final byte[] data, final Class<T> type) {
        requireNonNull(data);
        requireNonNull(type);

        try {
            return mapper.readValue(data, type);
        } catch (IOException e) {
            logger.error(e.getMessage());
            return (Event) () -> null;
        }
    }
}
