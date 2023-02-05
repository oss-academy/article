package com.tutorial.mvp.view;

public interface View<T, E> {
    T[] getModel();

    void setModel(T... models);

    E getInput();

    void setInput();

    void represent();

}
