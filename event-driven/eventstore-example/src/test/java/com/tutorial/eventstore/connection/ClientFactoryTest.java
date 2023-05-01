package com.tutorial.eventstore.connection;

import com.eventstore.dbclient.EventStoreDBClient;
import com.tutorial.eventstore.BaseTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ClientFactoryTest extends BaseTest {

    @Test
    void ShouldCreatedClientForLocalHostDatabase() {
        EventStoreDBClient client = ClientFactory.createClientForLocalHost();
        assertNotNull(client);
    }
}
