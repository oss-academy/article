package com.tutorial.eventstore.config;

import static com.tutorial.eventstore.util.ResourceUtils.readProperties;

public final class Configuration {

    private Configuration() {
    }

    public static void load() {
        System.setProperties(readProperties("application.properties"));
    }
}
