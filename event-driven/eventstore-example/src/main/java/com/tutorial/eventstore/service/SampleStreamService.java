package com.tutorial.eventstore.service;

import com.eventstore.dbclient.EventData;
import com.eventstore.dbclient.EventStoreDBClient;
import com.eventstore.dbclient.ReadStreamOptions;
import com.tutorial.eventstore.connection.ClientFactory;
import com.tutorial.eventstore.event.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.tutorial.eventstore.util.JsonUtils.toType;
import static java.util.Objects.requireNonNull;

public final class SampleStreamService {

    public static final SampleStreamService INSTANCE = new SampleStreamService();

    public static final String STREAM_NAME = "Sample_Stream";

    private final Logger logger = LoggerFactory.getLogger(SampleStreamService.class.getSimpleName());

    private final EventStoreDBClient client = ClientFactory.getDefaultClient();

    private SampleStreamService() {

    }

    public void append(Event event) {
        requireNonNull(event);

        EventData eventData = EventData
                .builderAsJson(STREAM_NAME, event)
                .build();
        try {
            client.appendToStream(STREAM_NAME, eventData)
                    .get();
            if (logger.isInfoEnabled()) {
                logger.info("an event added to {}: {}",STREAM_NAME,  event);
            }
        } catch (InterruptedException | ExecutionException exception) {
            logger.error("appending to stream failed due to: {}", exception.getMessage());
        }
    }

    public List<Event> getAll(Class<? extends Event> type) {
        requireNonNull(type);

        ReadStreamOptions options = ReadStreamOptions.get()
                .fromStart()
                .notResolveLinkTos();
        try {
            return client.readStream(STREAM_NAME, options)
                    .get()
                    .getEvents()
                    .stream()
                    .map(event -> (Event) toType(event.getOriginalEvent().getEventData(), type))
                    .toList();

        } catch (InterruptedException | ExecutionException exception) {
            logger.error("getting all events from stream failed due to: {}", exception.getMessage());
            return new ArrayList<>();
        }
    }

    public void deleteStream() {
        client.deleteStream(STREAM_NAME);
        if (logger.isInfoEnabled()) {
            logger.info("{} stream deleted", STREAM_NAME);
        }
    }

}
