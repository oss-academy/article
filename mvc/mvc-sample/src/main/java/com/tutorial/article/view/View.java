package com.tutorial.article.view;

public sealed interface View<T> permits ConsoleView {
    T[] models();

    void models(T... models);

    void viewModels();
}
