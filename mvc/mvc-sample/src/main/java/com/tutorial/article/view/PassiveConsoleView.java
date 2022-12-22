package com.tutorial.article.view;

import com.tutorial.article.model.Model;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

import static java.lang.String.format;
import static java.util.logging.Logger.getLogger;
import static java.util.stream.Collectors.joining;

public final class PassiveConsoleView implements View<Model> {

    private final Logger logger = getLogger(PassiveConsoleView.class.getSimpleName());

    private Model[] models = new Model[0];

    public PassiveConsoleView() {
        logger.setLevel(Level.ALL);
    }

    @Override
    public Model[] getModels() {
        return models;
    }


    @Override
    public void setModel(Model... models) {
        this.models = models;
    }

    @Override
    public void addModel(Model... models) {
        this.models = Stream.concat(Stream.of(this.models), Stream.of(models))
                .toList()
                .toArray(new Model[0]);
    }

    @Override
    public void view() {
        if (logger.isLoggable(Level.INFO)) {
            var message = "Models" +
                    "\n" +
                    "=================================================" +
                    "\n\n" +
                    format("%s", Stream.of(models).map(Model::toString).collect(joining("\n"))) +
                    "\n\n" +
                    "=================================================" +
                    "\n";
            logger.info(message);
        }
    }

}
