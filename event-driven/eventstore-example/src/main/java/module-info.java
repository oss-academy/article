module eventstore.example {
    exports com.tutorial.eventstore.connection;
    exports com.tutorial.eventstore.event;
    exports com.tutorial.eventstore.service;
    exports com.tutorial.eventstore.util;

    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.annotation;
    requires org.slf4j;
    requires reload4j;
    requires db.client.java;
}