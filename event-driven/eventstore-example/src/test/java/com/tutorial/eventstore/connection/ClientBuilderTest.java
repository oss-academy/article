package com.tutorial.eventstore.connection;

import com.eventstore.dbclient.EventStoreDBClient;
import com.tutorial.eventstore.BaseTest;
import com.tutorial.eventstore.LocalHostParameter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ClientBuilderTest extends BaseTest {

    @Test
    void GivenLocalInfo_WhenAssignParameter_ThenReturnsClient() {
        var givenProtocol = LocalHostParameter.PROTOCOL;
        var givenHost = LocalHostParameter.HOST;
        var givenPort = LocalHostParameter.PORT;
        var givenTls = LocalHostParameter.TLS;

        var result = new ClientBuilder()
                .setProtocol(givenProtocol)
                .setHost(givenHost)
                .setPort(givenPort)
                .setTls(givenTls)
                .build();

        assertNotNull(result);
    }
}
