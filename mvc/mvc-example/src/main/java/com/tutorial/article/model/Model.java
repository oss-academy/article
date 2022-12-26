package com.tutorial.article.model;

import java.util.Objects;

public record Model(Integer number, String text) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Model model = (Model) o;
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
