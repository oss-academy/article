package com.tutorial.eventstore.service;

import com.eventstore.dbclient.EventStoreDBClient;
import com.eventstore.dbclient.ReadAllOptions;
import com.eventstore.dbclient.ReadStreamOptions;
import com.eventstore.dbclient.WriteResult;
import com.tutorial.eventstore.event.Event;
import com.tutorial.eventstore.stream.EventStream;
import com.tutorial.eventstore.stream.SampleEventStream;
import com.tutorial.eventstore.util.EventStoreClientFactory;
import com.tutorial.eventstore.util.StreamUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

import static com.tutorial.eventstore.util.EventTransformer.toEventDataIterator;
import static com.tutorial.eventstore.util.validator.StreamValidator.isValidStream;
import static com.tutorial.eventstore.util.validator.StringValidator.shouldNotBeNullOrEmpty;

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
            var result = Optional.ofNullable(
                    client.appendToStream(stream.getId(), toEventDataIterator(stream.getEvents())).get()
            );

            if (logger.isInfoEnabled()) {
                logger.info("{} events added to {}", stream.getEvents().size(), stream.getId());
            }

            return result;
        } catch (Exception exception) {
            logger.error("appending to the stream {} failed due to: {}", stream.getId(), exception.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<EventStream<Object, Event<Object>>> read(final String streamId) {
        shouldNotBeNullOrEmpty(streamId, "stream Id should not be blank");

        return Optional.of(new SampleEventStream<>(streamId, getEvents(streamId)));
    }

    @Override
    public Set<String> getAllStreamId() {

        try {
            return client.readAll(ReadAllOptions.get().fromStart().notResolveLinkTos())
                    .get()
                    .getEvents()
                    .stream()
                    .map(StreamUtils::extractOriginalEvent)
                    .filter(Objects::nonNull)
                    .collect(Collectors.groupingBy(Event::getStreamId))
                    .keySet();
        } catch (Exception exception) {
            logger.error("reading all streams failed due to: {}", exception.getMessage());
            return new HashSet<>();
        }
    }

    @Override
    public void delete(final String streamId) {
        shouldNotBeNullOrEmpty(streamId, "stream Id should not be blank");

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
        shouldNotBeNullOrEmpty(streamId, "stream Id should not be blank");

        try {
            return client.readStream(streamId, ReadStreamOptions.get().fromStart().notResolveLinkTos())
                    .get()
                    .getEvents()
                    .stream()
                    .map(StreamUtils::extractOriginalEvent)
                    .filter(Objects::nonNull)
                    .toList();
        } catch (Exception exception) {
            logger.error("reading from the stream {} failed due to: {}", streamId, exception.getMessage());
            return new ArrayList<>();
        }
    }

}
