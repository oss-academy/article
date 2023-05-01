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

public final class SampleStreamService {

    public static final SampleStreamService INSTANCE = new SampleStreamService();

    public static final String STREAM_NAME = "Sample_Stream";

    private final Logger logger = LoggerFactory.getLogger(SampleStreamService.class.getSimpleName());

    private final EventStoreDBClient client = ClientFactory.getDefaultClient();

    private SampleStreamService() {

    }

    public void append(Object event) {
        EventData eventData = EventData
                .builderAsJson(STREAM_NAME, event)
                .build();

        try {
            client.appendToStream(STREAM_NAME, eventData)
                    .get();
        } catch (InterruptedException | ExecutionException e) {
            logger.error(e.getMessage());
        }
    }

    public List<Event> getAll(Class<? extends Event> type) {
        ReadStreamOptions readStreamOptions = ReadStreamOptions.get()
                .fromStart()
                .notResolveLinkTos();

        try {
            return client.readStream(STREAM_NAME, readStreamOptions)
                    .get()
                    .getEvents()
                    .stream()
                    .map(event -> (Event) toType(event.getOriginalEvent().getEventData(), type))
                    .toList();

        } catch (InterruptedException | ExecutionException e) {
            logger.error(e.getMessage());
            return new ArrayList<>();
        }
    }

    public void deleteStream() {
        client.deleteStream(STREAM_NAME);
    }

}
