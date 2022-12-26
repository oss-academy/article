package com.tutorial.article.view;

import java.util.Optional;

public record Response(String text) {

    public static Optional<Response> ok() {
        return Optional.of(new Response("ok"));
    }

    public static Optional<Response> error(String mes) {
        return Optional.of(new Response(mes));
    }

    public static Optional<Response> bye() {
        return Optional.of(new Response("bye"));
    }

    public static Optional<Response> empty() {
        return Optional.empty();
    }

    @Override
    public String toString() {
        return String.format("%s", text);
    }
}
