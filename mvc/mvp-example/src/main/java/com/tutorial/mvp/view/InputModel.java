package com.tutorial.mvp.view;

import java.util.Objects;

public record InputModel(String text, String number) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InputModel model = (InputModel) o;
        return number.equals(model.number) && text.equals(model.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, text);
    }

    @Override
    public String toString() {
        return String.format("{\"number\":%s,\"text\":\"%s\"}", number, text);
    }
}
