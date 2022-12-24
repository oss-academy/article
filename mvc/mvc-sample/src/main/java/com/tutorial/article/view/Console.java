package com.tutorial.article.view;

import com.tutorial.article.controller.Controller;
import com.tutorial.article.model.Model;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiFunction;

import static com.tutorial.article.util.ArrayUtils.convertToString;
import static com.tutorial.article.view.Response.*;
import static java.util.Collections.synchronizedMap;
import static java.util.Objects.requireNonNull;

public class Console implements Runnable, Cacheable {

    private static final String COMMAND_SEPARATOR = " ";

    private final AtomicBoolean isAlive = new AtomicBoolean(true);

    private final Scanner scanner = new Scanner(System.in);

    private final Controller controller;

    private final View<Model> view;

    private final List<Model> cache = new ArrayList<>();

    private Map<Command, BiFunction<View<Model>, String[], Optional<Response>>> map = synchronizedMap(new HashMap<>());

    public Console(Controller controller, View<Model> view) {
        this.controller = controller;
        this.view = view;
        System.out.print(welcomeMessage());
        initCommands();
    }

    public void run() {
        while (isAlive.get()) {
            String[] commandParts = scanner.nextLine().trim().split(COMMAND_SEPARATOR);
            try {
                getFunction(commandParts[0])
                        .apply(view, Arrays.copyOfRange(commandParts, 1, commandParts.length))
                        .ifPresent(System.out::println);

            } catch (Exception e) {
                System.out.println("command: " + commandParts[0] + "\nerror message: " + e.getMessage() + "\n");
            }
        }
    }

    @Override
    public List<Model> cache() {
        return cache;
    }

    @Override
    public void cache(Model... models) {
        this.cache.addAll(Arrays.stream(models).toList());
    }

    @Override
    public void clear() {
        this.cache.clear();
    }

    @Override
    public void viewCache() {
        System.out.print(convertToString(cache.toArray(new Model[0]), "cached models"));
    }

    private BiFunction<View<Model>, String[], Optional<Response>> getFunction(String cmd) {
        requireNonNull(cmd);
        return map.get(Command.valueOf(cmd.toUpperCase()));
    }

    private String welcomeMessage() {
        return """
                ##################################################
                hi, please write a command
                write help to show all commands
                ##################################################
                """;
    }

    private void initCommands() {
        map.put(Command.NEW, (view, args) -> {
            System.out.print("number:");
            var number = scanner.nextLine();

            System.out.print("text:");
            var text = scanner.nextLine();

            cache(new Model(Integer.parseInt(number), text));
            return ok();
        });

        map.put(Command.SAVE, (view, args) -> {
            view.models(cache().toArray(new Model[0]));
            controller.save(view);
            clear();
            return ok();
        });

        map.put(Command.CLEAR, (view, args) -> {
            clear();
            return ok();
        });

        map.put(Command.SHOW, (view, args) -> {
            if (args.length > 0) {
                controller.getById(view, Integer.parseInt(args[0]));
            } else {
                controller.getAll(view);
            }
            view.viewModels();
            return ok();
        });

        map.put(Command.CACHE, (view, args) -> {
            viewCache();
            return ok();
        });

        map.put(Command.DELETE, (view, args) -> {
            if (args.length > 0) {
                controller.deleteById(view, Integer.parseInt(args[0]));
            } else {
                return Optional.of(new Response("delete all is not allowed"));
            }

            return ok();
        });

        map.put(Command.HELP, (view, args) -> {
            var str = Command.HELP.name().toLowerCase() + ": show help\n" +
                    Command.CACHE.name().toLowerCase() + ": show cached models\n" +
                    Command.SHOW.name().toLowerCase() + ": show persisted models\n" +
                    Command.NEW.name().toLowerCase() + ": save new model to cache\n" +
                    Command.SAVE.name().toLowerCase() + ": save cached models to storage\n" +
                    Command.CLEAR.name().toLowerCase() + ": clear cache\n" +
                    Command.EXIT.name().toLowerCase() + ": exit";

            System.out.println(str);
            return empty();
        });

        map.put(Command.EXIT, (view, args) -> {
            isAlive.set(false);
            return bye();
        });
    }


}