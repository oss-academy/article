package com.tutorial.article.view;

public interface View<T> {
    T[] getModels();

    void update(T... models);

    void view();
}
