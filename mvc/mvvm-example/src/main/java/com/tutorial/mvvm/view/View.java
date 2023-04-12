package com.tutorial.mvvm.view;

import com.tutorial.mvvm.model.Model;

public interface View<T, E> {
    T[] getModel();

    void setModel(T... models);

    E getInput();

    void setInput();

    void represent();

    void update(Model[] models);

    void bindAction();
}
