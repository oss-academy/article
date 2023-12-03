package com.tutorial.eventstore.testutil;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestEventStore {
    @Container
    private final GenericContainer<?> eventStore = new GenericContainer<>(DockerImageName.parse("eventstore/eventstore"))
            .withExposedPorts(2113)
            .withEnv("EVENTSTORE_CLUSTER_SIZE", "1")
            .withEnv("EVENTSTORE_RUN_PROJECTIONS", "All")
            .withEnv("EVENTSTORE_START_STANDARD_PROJECTIONS", "true")
            .withEnv("EVENTSTORE_EXT_TCP_PORT", "1113")
            .withEnv("EVENTSTORE_HTTP_PORT", "2113")
            .withEnv("EVENTSTORE_INSECURE", "true")
            .withEnv("EVENTSTORE_ENABLE_EXTERNAL_TCP", "true")
            .withEnv("EVENTSTORE_ENABLE_ATOM_PUB_OVER_HTTP", "true")
            .waitingFor(Wait.forHttp("/").forStatusCode(200));

    public void start() {
        eventStore.start();
        assertNotNull(eventStore);
        assertNotNull(eventStore.getHost());
        assertNotNull(eventStore.getFirstMappedPort());
        System.setProperty("db.url", getUrl());
    }

    public void shutdown() {
        eventStore.stop();
    }

    public String getUrl() {
        return String.format("esdb://%s:%s?tls=false", eventStore.getHost(), eventStore.getFirstMappedPort());
    }
}
