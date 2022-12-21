package com.tutorial.article.view;

import com.tutorial.article.model.Model;

import java.util.Arrays;
import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

public final class ConsoleView implements View<Model> {

    private final Logger logger = getLogger(ConsoleView.class.getSimpleName());

    private Model[] models = new Model[0];

    @Override
    public Model[] getModels() {
        return models;
    }

    @Override
    public void update(Model... models) {
        this.models = models;
    }

    @Override
    public void view() {
        logger.info("\n" + Arrays.toString(models) + "\n");
    }

}
