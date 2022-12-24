package com.tutorial.article.view;

import com.tutorial.article.model.Model;

import static com.tutorial.article.util.ArrayUtils.convertToString;

public final class ConsoleView implements View<Model> {

    private Model[] models = new Model[0];

    @Override
    public Model[] models() {
        return models;
    }

    @Override
    public void models(Model... models) {
        this.models = models;
    }

    @Override
    public void viewModels() {
        System.out.print(convertToString(models, "persisted models"));
    }

}
