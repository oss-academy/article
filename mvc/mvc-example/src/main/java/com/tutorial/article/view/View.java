package com.tutorial.article.view;

public sealed interface View<T> permits ConsoleView, DesktopView {
    T[] models();

    void models(T... models);

    void view();
}
