package com.tutorial.article.model;

import java.util.Objects;

public record Model(Integer id, String text, Integer number) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Model model = (Model) o;
        return id.equals(model.id) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("{\"id\":%s,\"text\":\"%s\",\"number\":\"%s\"}", id, text, number);
    }
}
