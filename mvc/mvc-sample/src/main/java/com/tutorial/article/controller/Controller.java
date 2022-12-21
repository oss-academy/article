package com.tutorial.article.controller;

import com.tutorial.article.DB;
import com.tutorial.article.model.Model;
import com.tutorial.article.view.View;

public final class Controller {

    public static final Controller INSTANCE = new Controller();

    private Controller() {
    }

    public void getAll(View view) {
        view.update(DB.MODELS.values().toArray(new Model[0]));
    }

    public void getById(View view, Integer id) {
        view.update(DB.MODELS.get(id));
    }

}
