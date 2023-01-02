package com.tutorial.article.controller;

import com.tutorial.article.db.DB;
import com.tutorial.article.model.Model;
import com.tutorial.article.view.InputModel;
import com.tutorial.article.view.View;

import java.util.Optional;

import static com.tutorial.article.util.StringUtils.requireNonEmpty;
import static java.util.Objects.requireNonNull;

public final class Controller {

    public static final Controller INSTANCE = new Controller();

    private Controller() {
    }

    public int save(InputModel inputModel) {
        requireNonNull(inputModel);

        var input = requireNonNull(inputModel);
        var number = Integer.parseInt(requireNonEmpty(input.number().trim()));
        var text = requireNonEmpty(input.text().trim());


        var id = DB.MODEL_ID.incrementAndGet();
        var model = new Model(id, text, number);

        DB.MODELS.get().put(id, model);

        return id;
    }

    public Model[] getAll() {
        return DB.MODELS.get().values().toArray(new Model[0]);
    }

    public Optional<Model> getById(Integer id) {
        requireNonNull(id);

        if (DB.MODELS.get().containsKey(id)) {
            return Optional.ofNullable(DB.MODELS.get().get(id));
        } else {
            return Optional.empty();
        }
    }

    public void deleteById(Integer id) {
        requireNonNull(id);

        DB.MODELS.get().remove(id);
    }
}
