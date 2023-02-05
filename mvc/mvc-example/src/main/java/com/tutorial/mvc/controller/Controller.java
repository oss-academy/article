package com.tutorial.mvc.controller;

import com.tutorial.mvc.db.DB;
import com.tutorial.mvc.model.Model;
import com.tutorial.mvc.view.InputModel;
import com.tutorial.mvc.view.View;
import com.tutorial.mvc.util.StringUtils;

import static java.util.Objects.requireNonNull;

public final class Controller {

    public static final Controller INSTANCE = new Controller();

    private Controller() {
    }

    public void save(View<Model, InputModel> view) {
        requireNonNull(view);

        var input = requireNonNull(view.getInput());
        var number = Integer.parseInt(StringUtils.requireNonEmpty(input.number().trim()));
        var text = StringUtils.requireNonEmpty(input.text().trim());


        int id = DB.MODEL_ID.incrementAndGet();
        var model = new Model(id, text, number);

        DB.MODELS.get().put(id, model);
    }

    public void getAll(View<Model, InputModel> view) {
        requireNonNull(view);

        view.setModel(DB.MODELS.get().values().toArray(new Model[0]));
    }

    public void getById(View<Model, InputModel> view, Integer id) {
        requireNonNull(view);
        requireNonNull(id);

        if (DB.MODELS.get().containsKey(id)) {
            view.setModel(DB.MODELS.get().get(id));
        } else {
            view.setModel(new Model[0]);
        }
    }

    public void deleteById(View<Model, InputModel> view, Integer id) {
        requireNonNull(view);
        requireNonNull(id);

        DB.MODELS.get().remove(id);
    }
}
