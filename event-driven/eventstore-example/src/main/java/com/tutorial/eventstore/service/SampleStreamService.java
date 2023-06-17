package com.tutorial.eventstore.service;

import com.eventstore.dbclient.EventStoreDBClient;
import com.eventstore.dbclient.ReadStreamOptions;
import com.eventstore.dbclient.WriteResult;
import com.tutorial.eventstore.event.Event;
import com.tutorial.eventstore.stream.EventStream;
import com.tutorial.eventstore.stream.SampleEventStream;
import com.tutorial.eventstore.util.EventStoreClientFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.tutorial.eventstore.util.EventTransformer.toEventDataIterator;
import static com.tutorial.eventstore.util.JsonUtils.toObjectType;
import static com.tutorial.eventstore.util.StreamValidator.isValidStream;
import static com.tutorial.eventstore.util.StringValidator.shouldNotBeNullOrEmpty;
import static java.util.Optional.ofNullable;

public final class SampleStreamService implements StreamService<Object, Event<Object>, EventStream<Object, Event<Object>>> {

    private final Logger logger = LoggerFactory.getLogger(SampleStreamService.class.getSimpleName());

    private final EventStoreDBClient client = EventStoreClientFactory.createClient();

    private SampleStreamService() {
    }

    private static class SingletonHolder {
        public static final SampleStreamService INSTANCE = new SampleStreamService();
    }

    public static SampleStreamService getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public Optional<WriteResult> append(final EventStream<Object, Event<Object>> stream) {
        isValidStream(stream);

        try {
            return ofNullable(client.appendToStream(stream.getId(), toEventDataIterator(stream.getEvents())).get())
                    .map(result -> {
                        if (logger.isInfoEnabled()) {
                            logger.info("{} events added to {}", stream.getEvents().size(), stream.getId());
                        }
                        return result;
                    });
        } catch (Exception exception) {
            logger.error("appending to the stream {} failed due to: {}", stream.getId(), exception.getMessage());
            return Optional.empty();
        }

    }

    @Override
    public Optional<EventStream<Object, Event<Object>>> read(final String streamId) {
        shouldNotBeNullOrEmpty(streamId, "stream Id");

        return Optional.of(new SampleEventStream(streamId, getEvents(streamId)));
    }

    @Override
    public void delete(final String streamId) {
        shouldNotBeNullOrEmpty(streamId, "stream Id");

        try {
            client.deleteStream(streamId);
            if (logger.isInfoEnabled()) {
                logger.info("{} stream deleted", SampleEventStream.class.getSimpleName());
            }
        } catch (Exception exception) {
            logger.error("deleting the stream {} failed due to: {}", streamId, exception.getMessage());
        }
    }

    private List<Event<Object>> getEvents(final String streamId) {
        shouldNotBeNullOrEmpty(streamId, "stream Id");

        try {
            return client.readStream(streamId, ReadStreamOptions.get().fromStart().notResolveLinkTos())
                    .get()
                    .getEvents()
                    .stream()
                    .map(event -> {
                        var originalEvent = event.getOriginalEvent();
                        return (Event<Object>) toObjectType(originalEvent.getEventData(), originalEvent.getEventType());
                    })
                    .filter(Objects::nonNull)
                    .toList();

        } catch (Exception exception) {
            logger.error("reading from the stream {} failed due to: {}", streamId, exception.getMessage());
            return new ArrayList<>();
        }
    }

}
