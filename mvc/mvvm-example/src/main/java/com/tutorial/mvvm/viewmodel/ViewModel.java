package com.tutorial.mvvm.viewmodel;

import com.tutorial.mvvm.db.DB;
import com.tutorial.mvvm.model.Model;
import com.tutorial.mvvm.view.InputModel;
import com.tutorial.mvvm.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.tutorial.mvvm.util.StringUtils.requireNonEmpty;
import static java.util.Objects.requireNonNull;

public final class ViewModel {

    public static final ViewModel INSTANCE = new ViewModel();

    private final List<View<Model, InputModel>> views = new ArrayList<>();

    private ViewModel() {
    }

    public void addView(View<Model, InputModel> view) {
        views.add(view);
    }

    public int save(InputModel inputModel) {
        requireNonNull(inputModel);

        var input = requireNonNull(inputModel);
        var number = Integer.parseInt(requireNonEmpty(input.number().trim()));
        var text = requireNonEmpty(input.text().trim());


        var id = DB.MODEL_ID.incrementAndGet();
        var model = new Model(id, text, number);

        DB.MODELS.get().put(id, model);

        updateViews();

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

    public void updateViews() {
        views.forEach(view -> view.update(getAll()));
    }

}
