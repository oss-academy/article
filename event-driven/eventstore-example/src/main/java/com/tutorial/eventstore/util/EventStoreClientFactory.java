package com.tutorial.eventstore.util;

import com.eventstore.dbclient.EventStoreDBClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.eventstore.dbclient.EventStoreDBConnectionString.parseOrThrow;
import static com.tutorial.eventstore.util.CollectionUtils.joinArray;
import static java.util.Objects.requireNonNull;

public final class EventStoreClientFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventStoreClientFactory.class.getSimpleName());

    private EventStoreClientFactory() {
    }

    public static EventStoreDBClient createClient() {
        var settings = requireNonNull(parseOrThrow(System.getProperty("db.url")), "client setting is null");

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("hosts: {}", joinArray(settings.getHosts(), ","));
            LOGGER.info("credentials: {}", settings.getDefaultCredentials());
            LOGGER.info("secure connection (TLS): {}", settings.isTls());
            LOGGER.info("connection was established");
        }

        var client = requireNonNull(EventStoreDBClient.create(settings), "client is null");

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("client is ready");
        }

        return client;
    }

}
