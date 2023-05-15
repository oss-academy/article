package com.tutorial.eventstore.connection;

import com.tutorial.eventstore.LocalHostParameter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ClientBuilderTest {

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
