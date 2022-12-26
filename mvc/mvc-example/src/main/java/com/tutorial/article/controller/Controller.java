package com.tutorial.article.controller;

import com.tutorial.article.db.DB;
import com.tutorial.article.model.Model;
import com.tutorial.article.view.View;

import static java.util.Objects.requireNonNull;

public final class Controller {

    public static final Controller INSTANCE = new Controller();

    private Controller() {
    }

    public void save(View<Model> view) {
        requireNonNull(view);
        requireNonNull(view.models());

        Model[] models = view.models();
        for (Model model : models) {
            DB.MODELS.get().put(DB.MODEL_ID.incrementAndGet(), model);
        }
    }

    public void getAll(View<Model> view) {
        requireNonNull(view);

        view.models(DB.MODELS.get().values().toArray(new Model[0]));
    }

    public void getById(View<Model> view, Integer id) {
        requireNonNull(view);
        requireNonNull(id);

        if (DB.MODELS.get().containsKey(id)) {
            view.models(DB.MODELS.get().get(id));
        } else {
            view.models(new Model[0]);
        }
    }

    public void deleteById(View<Model> view, Integer id) {
        requireNonNull(view);
        requireNonNull(id);

        DB.MODELS.get().remove(id);
    }
}
