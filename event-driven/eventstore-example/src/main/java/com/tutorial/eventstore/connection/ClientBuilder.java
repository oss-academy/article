package com.tutorial.eventstore.connection;

import com.eventstore.dbclient.EventStoreDBClient;
import com.eventstore.dbclient.EventStoreDBClientSettings;
import com.eventstore.dbclient.EventStoreDBConnectionString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

import static com.eventstore.dbclient.EventStoreDBConnectionString.parseOrThrow;
import static com.tutorial.eventstore.util.CollectionUtils.joinArray;
import static com.tutorial.eventstore.util.CollectionUtils.joinEntry;
import static java.util.Objects.requireNonNull;

public final class ClientBuilder {

    private final Logger logger = LoggerFactory.getLogger(ClientBuilder.class.getSimpleName());

    private String protocol;

    private String host;

    private String port;

    private final Map<String, String> parameters = new HashMap<>();

    public ClientBuilder setProtocol(String protocol) {
        this.protocol = protocol;
        return this;
    }

    public ClientBuilder setHost(String host) {
        this.host = host;
        return this;
    }

    public ClientBuilder setPort(int port) {
        this.port = String.valueOf(port);
        return this;
    }

    public ClientBuilder setTls(boolean tls) {
        this.parameters.put("tls", String.valueOf(tls));
        return this;
    }

    public EventStoreDBClient build() {
        var urlBuilder = new StringBuilder()
                .append(protocol)
                .append("://")
                .append(host)
                .append(":")
                .append(port);

        if (!parameters.isEmpty()) {
            urlBuilder.append("?")
                    .append(joinEntry(parameters, "=", ","));
        }

        EventStoreDBClientSettings settings = requireNonNull(parseOrThrow(urlBuilder.toString()));

        if (logger.isInfoEnabled()) {
            logger.info("hosts: {}", joinArray(settings.getHosts(), ","));
            logger.info("credentials: {}", settings.getDefaultCredentials());
            logger.info("secure connection (TLS): {}", settings.isTls());
            logger.info("connection was established");
        }

        var client = requireNonNull(EventStoreDBClient.create(settings));

        if (logger.isInfoEnabled()) {
            logger.info("client is ready");
        }

        return client;
    }
}