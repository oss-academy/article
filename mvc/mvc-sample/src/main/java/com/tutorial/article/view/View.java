package com.tutorial.article.view;

import com.tutorial.article.model.Model;

import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Stream;

import static java.util.logging.Logger.getLogger;
import static java.util.stream.Collectors.joining;

public final class View {

    private final Logger logger = getLogger(View.class.getSimpleName());

    private final String id = UUID.randomUUID().toString();

    private Model[] models = new Model[0];

    public String getId() {
        return id;
    }

    public Model[] getModels() {
        return models;
    }

    public void update(Model... models) {
        this.models = models;
    }

    public void print() {
        logger.info("\nView: " + id + "\n" + modelsToString() + "\n");
    }

    public String modelsToString() {
        return Stream.of(models).map(Model::toString).collect(joining("\n"));
    }
}
