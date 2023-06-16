package com.tutorial.eventstore.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

public final class ResourceUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceUtils.class.getSimpleName());

    private ResourceUtils() {
    }

    public static InputStream readResource(String fileName) {
        return Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream(fileName);
    }

    public static Properties readProperties(String fileName) {
        try (InputStream inputStream = readResource(fileName)) {
            var properties = new Properties();
            properties.load(inputStream);
            return properties;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

        return new Properties();
    }
}
