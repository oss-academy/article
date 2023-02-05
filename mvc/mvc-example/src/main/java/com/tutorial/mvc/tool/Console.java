package com.tutorial.mvc.tool;

import com.tutorial.mvc.controller.Controller;
import com.tutorial.mvc.view.console.Response;
import com.tutorial.mvc.view.console.ConsoleView;
import com.tutorial.mvc.view.console.command.Command;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

import static com.tutorial.mvc.view.console.Response.*;
import static com.tutorial.mvc.view.console.command.CommandName.*;
import static java.util.stream.Collectors.toMap;

public class Console implements Runnable {

    public static final String WELCOME_MESSAGE = """
            ##################################################
            hi, please write a command
            write help to show all commands
            ##################################################
            """;
    private final AtomicBoolean isAlive = new AtomicBoolean(true);

    private final Scanner scanner = new Scanner(System.in);

    private final Controller controller;

    private final ConsoleView view;

    private final List<Command> commandGroup;

    public Console(Controller controller, ConsoleView view) {
        this.controller = controller;
        this.view = view;
        this.commandGroup = view.getCommandGroup();
        bindAction();
    }

    public void run() {
        System.out.print(WELCOME_MESSAGE);
        while (isAlive.get()) {
            String[] commandParts = scanner.nextLine()
                    .trim()
                    .split(" ");
            try {
                commandGroup.stream()
                        .filter(command -> command.getName().equals(commandParts[0]))
                        .findFirst()
                        .ifPresent(command -> {
                            command.setArgs(createArgsMap(Arrays.copyOfRange(commandParts, 1, commandParts.length)));
                            Optional<Response> response = command.execute();
                            response.ifPresent(System.out::println);
                        });


            } catch (Exception e) {
                System.out.println("command: " + commandParts[0] + "\nerror message: " + e.getMessage() + "\n");
            }
        }
    }

    private Map<String, Object> createArgsMap(String[] args) {

        if (args == null || args.length == 0) {
            return new HashMap<>();
        }

        return Stream.of(args)
                .map(s -> s.split("="))
                .collect(toMap(s -> s[0], s -> s[1]));

    }

    private void bindAction() {
        view.getNewCommand()
                .setAction(param -> {
                    view.setInput();
                    return ok();
                });

        view.getSaveCommand()
                .setAction(param -> {
                    controller.save(view);
                    return ok();
                });

        view.getViewCommand()
                .setAction(param -> {
                    if (param.containsKey("id")) {
                        controller.getById(view, Integer.parseInt(param.get("id").toString()));
                    } else {
                        controller.getAll(view);
                    }
                    view.represent();
                    return ok();
                });

        view.getDeleteCommand()
                .setAction(param -> {
                    if (param.containsKey("id")) {
                        controller.deleteById(view, Integer.parseInt(param.get("id").toString()));
                    } else {
                        System.out.println("delete all is not allowed");
                    }
                    return ok();
                });

        view.getHelpCommand()
                .setAction(param -> {
                    var str = HELP.getValue() + ": show help\n" +
                            VIEW.getValue() + " [id=number]: view persisted models\n" +
                            NEW.getValue() + ": initial new model\n" +
                            SAVE.getValue() + ": save new model to storage\n" +
                            DELETE.getValue() + " [id=number]: delete persisted models from storage\n" +
                            EXIT.getValue() + ": exit";

                    System.out.println(str);
                    return empty();
                });

        view.getExitCommand()
                .setAction(param -> {
                    isAlive.set(false);
                    return bye();
                });
    }

}