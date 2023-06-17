package com.tutorial.eventstore.util;

import com.tutorial.eventstore.testutil.TestEventStoreHelper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class EventStoreClientFactoryTest {

    private static final TestEventStoreHelper eventStore = new TestEventStoreHelper();

    @BeforeAll
    static void setup() {
        eventStore.start();
    }

    @AfterAll
    static void tearDown() {
        eventStore.shutdown();
    }

    @Test
    void ShouldCreatedClientForLocalHostDatabase() {
        var actual = EventStoreClientFactory.createClient();
        assertNotNull(actual);
    }
}
