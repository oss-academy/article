package com.tutorial.mvc.view.console.command;

import com.tutorial.mvc.view.console.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

public abstract class Command {

    private final String name;

    private Map<String, Object> args = new HashMap<>();

    private Function<Map<String, Object>, Optional<Response>> action = map -> Response.empty();

    public Command(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setArgs(Map<String, Object> args) {
        this.args = args;
    }

    public void setAction(Function<Map<String, Object>, Optional<Response>> action) {
        this.action = action;
    }

    public Optional<Response> execute() {
        return action.apply(args);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Command command = (Command) o;
        return name.equals(command.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
