package com.tutorial.article.controller;

import com.tutorial.article.db.DB;
import com.tutorial.article.model.Model;
import com.tutorial.article.view.View;

import static java.util.Objects.requireNonNull;

public final class Controller {

    public static final Controller INSTANCE = new Controller();

    private Controller() {
    }

    public void getAll(View<Model> view) {
        view.update(DB.MODELS.get().values().toArray(new Model[0]));
    }

    public void getById(View<Model> view, Integer id) {
        view.update(DB.MODELS.get().get(id));
    }

    public void save(View<Model> view) {
        requireNonNull(view.getModels());

        Model[] models = view.getModels();
        for (Model model : models) {
            DB.MODELS.get().put(DB.MODEL_ID.incrementAndGet(), model);
        }

    }
}
