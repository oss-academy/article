package com.tutorial.article.view;

public interface View<T> {
    T[] getModels();

    void setModel(T... models);

    void addModel(T... models);

    void view();
}
