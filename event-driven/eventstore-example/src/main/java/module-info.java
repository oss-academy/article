module eventstore.example {
    exports com.tutorial.eventstore.config;
    exports com.tutorial.eventstore.event;
    exports com.tutorial.eventstore.service;
    exports com.tutorial.eventstore.util;
    exports com.tutorial.eventstore.stream;
    exports com.tutorial.eventstore.util.validator;

    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.annotation;
    requires org.slf4j;
    requires log4j.api;
    requires log4j.core;
    requires log4j.slf4j.impl;
    requires db.client.java;
}