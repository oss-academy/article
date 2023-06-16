package com.tutorial.eventstore.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class EventStoreClientFactoryTest {

    @Container
    private final GenericContainer<?> eventStore = new GenericContainer(DockerImageName.parse("eventstore/eventstore"))
            .withExposedPorts(2113);

    @BeforeEach
    void setUp() {
        eventStore.start();
        assertNotNull(eventStore);
        assertNotNull(eventStore.getHost());
        assertNotNull(eventStore.getFirstMappedPort());
    }

    @AfterEach
    void tearDown() {
        eventStore.stop();
    }

    @Test
    void ShouldCreatedClientForLocalHostDatabase() {
        var actual = EventStoreClientFactory.createClient();
        assertNotNull(actual);
    }
}
