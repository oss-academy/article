package com.tutorial.eventstore.connection;

import com.eventstore.dbclient.EventStoreDBClient;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ClientFactoryTest {

    @Test
    void ShouldCreatedClientForLocalHostDatabase() {
        EventStoreDBClient result = ClientFactory.createClientForLocalHost();
        assertNotNull(result);
    }
}
