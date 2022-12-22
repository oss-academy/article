package com.tutorial.article.view;

import com.tutorial.article.controller.Controller;
import com.tutorial.article.model.Model;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.lang.String.format;
import static java.util.stream.Collectors.joining;

public class ActiveViewConsole implements Runnable, View<Model> {

    private enum Command {
        NEW,
        SAVE,
        CANCEL,
        SHOW,
        EXIT,
    }

    private record Response(String text) {
        @Override
        public String toString() {
            return String.format("%s", text);
        }
    }

    private Map<Command, Function<View<Model>, Optional<Response>>> functions = Collections.synchronizedMap(new HashMap<>());

    private final AtomicBoolean isAlive = new AtomicBoolean(true);

    private final Controller controller = Controller.INSTANCE;

    private final Scanner scanner = new Scanner(System.in);


    private Model[] models = new Model[0];

    public ActiveViewConsole() {
        functions.put(Command.NEW, view -> {
            System.out.print("insert number:");
            var number = scanner.nextInt();

            System.out.print("insert text:");
            var text = scanner.next();

            view.addModel(new Model(number, text));

            System.out.print("save/cancel/new [cancel]:");
            var command = scanner.next();
            return switch (Command.valueOf(command.toUpperCase())) {
                case SAVE -> functions.get(Command.SAVE).apply(this);
                case CANCEL -> functions.get(Command.CANCEL).apply(this);
                case NEW -> functions.get(Command.NEW).apply(this);
                default -> Optional.of(new Response("ok"));
            };
        });

        functions.put(Command.SAVE, view -> {
            controller.save(view);
            return Optional.of(new Response("ok"));
        });

        functions.put(Command.CANCEL, view -> {
            view.setModel(new Model[0]);
            return Optional.of(new Response("ok"));
        });

        functions.put(Command.SHOW, view -> {
            controller.getAll(view);
            view.view();
            return Optional.of(new Response("ok"));
        });

        functions.put(Command.EXIT, view -> {
            isAlive.set(false);
            return Optional.of(new Response("bye"));
        });
    }

    public void run() {
        System.out.println(Command.NEW.name().toLowerCase() + ": insert new model");
        System.out.println(Command.SAVE.name().toLowerCase() + ": save all inserted models");
        System.out.println(Command.CANCEL.name().toLowerCase() + ": rollback inserted model");
        System.out.println(Command.SHOW.name().toLowerCase() + ": print models");
        System.out.println(Command.EXIT.name().toLowerCase() + ": exit");

        while (isAlive.get()) {
            var input = scanner.next();
            try {
                functions.get(Command.valueOf(input.toUpperCase()))
                        .apply(this)
                        .ifPresent(System.out::println);
            } catch (Exception e) {
                System.out.println(input + "is invalid");
            }
        }
    }

    @Override
    public Model[] getModels() {
        return models;
    }

    @Override
    public void setModel(Model... models) {
        this.models = models;
    }

    @Override
    public void addModel(Model... models) {
        this.models = Stream.concat(Stream.of(this.models), Stream.of(models))
                .toList()
                .toArray(new Model[0]);
    }

    @Override
    public void view() {
        var message = "Models" +
                "\n" +
                "=================================================" +
                "\n\n" +
                format("%s", Stream.of(models).map(Model::toString).collect(joining("\n"))) +
                "\n\n" +
                "=================================================" +
                "\n";
        System.out.println(message);
    }

    public static void main(String[] args) {
        new Thread(new ActiveViewConsole()).start();
    }
}
