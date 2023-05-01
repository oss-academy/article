package com.tutorial.eventstore.connection;

import com.eventstore.dbclient.EventStoreDBClient;

public final class ClientFactory {

    private ClientFactory() {
    }

    public static EventStoreDBClient createClientForLocalHost(){
        return new ClientBuilder()
                .setProtocol("esdb")
                .setHost("localhost")
                .setPort(2113)
                .setTls(false)
                .build();
    }

     public static EventStoreDBClient getDefaultClient(){
        return createClientForLocalHost();
    }

}
