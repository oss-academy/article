package com.tutorial.eventstore.util;

import com.eventstore.dbclient.EventData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tutorial.eventstore.event.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.List;

import static java.util.Objects.requireNonNull;

public final class JsonUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class.getSimpleName());

    private static final ObjectMapper mapper = new ObjectMapper();

    private JsonUtils() {
    }

    public static String toJsonString(final Object o) {
        requireNonNull(o);

        try {
            return mapper.writeValueAsString(o);
        } catch (JsonProcessingException exception) {
            LOGGER.error("processing json failed due to: {}", exception.getMessage());
            return "";
        }
    }

    public static Object toObjectType(final byte[] data, String type) {
        requireNonNull(data);
        requireNonNull(type);
        if (type.isEmpty()){
            throw new IllegalArgumentException("type must not be null");
        }

        try {
            return mapper.readValue(data, Class.forName(type));
        } catch (Exception exception) {
            LOGGER.error("converting the json to {} failed due to: {}", type, exception.getMessage());
            return new Object();
        }
    }

}
