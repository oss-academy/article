package com.tutorial.mvp.view.console;

import com.tutorial.mvp.presenter.Presenter;
import com.tutorial.mvp.model.Model;
import com.tutorial.mvp.view.InputModel;
import com.tutorial.mvp.view.View;
import com.tutorial.mvp.view.console.command.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

import static com.tutorial.mvp.util.ArrayUtils.convertToString;
import static java.util.stream.Collectors.toMap;

public final class ConsoleView implements Runnable, View<Model, InputModel> {

    public static final String WELCOME_MESSAGE = """
            ##################################################
            hi, please write a command
            write help to show all commands
            ##################################################
            """;
    private final AtomicBoolean isAlive = new AtomicBoolean(true);

    private final Scanner scanner = new Scanner(System.in);

    private Model[] models = new Model[0];

    private final Presenter presenter = Presenter.INSTANCE;

    private String numberInput;

    private String textInput;

    private List<Command> commandGroup = new ArrayList<>();

    private Command newCommand = new NewCommand();

    private Command saveCommand = new SaveCommand();

    private Command viewCommand = new ViewCommand();

    private Command deleteCommand = new DeleteCommand();

    private Command helpCommand = new HelpCommand();

    private Command exitCommand = new ExitCommand();

    public ConsoleView() {
        bindAction();
        initCommandGroup();
    }

    @Override
    public Model[] getModel() {
        return models;
    }

    @Override
    public void setModel(Model... models) {
        this.models = models;
    }

    @Override
    public InputModel getInput() {
        return new InputModel(textInput.trim(), numberInput.trim());
    }

    @Override
    public void setInput() {
        System.out.print("set text =");
        textInput = scanner.nextLine();

        System.out.print("set number =");
        numberInput = scanner.nextLine();
    }

    @Override
    public void represent() {
        System.out.print(convertToString(models, "models"));
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
        newCommand.setAction(param -> {
                    setInput();
                    return Response.ok();
                });

        saveCommand.setAction(param -> {
                    presenter.save(getInput());
                    return Response.ok();
                });

        viewCommand.setAction(param -> {
                    if (param.containsKey("id")) {
                        Optional<Model> result = presenter.getById(Integer.parseInt(param.get("id").toString()));
                        result.ifPresent(this::setModel);
                    } else {
                        Model[] result = presenter.getAll();
                        setModel(result);
                    }
                    represent();
                    return Response.ok();
                });

        deleteCommand.setAction(param -> {
                    if (param.containsKey("id")) {
                        presenter.deleteById(Integer.parseInt(param.get("id").toString()));
                    } else {
                        System.out.println("delete all is not allowed");
                    }
                    return Response.ok();
                });

        helpCommand
                .setAction(param -> {var str = CommandName.HELP.getValue() + ": show help\n" +
                            CommandName.VIEW.getValue() + " [id=number]: view persisted models\n" +
                            CommandName.NEW.getValue() + ": initial new model\n" +
                            CommandName.SAVE.getValue() + ": save new model to storage\n" +
                            CommandName.DELETE.getValue() + " [id=number]: delete persisted models from storage\n" +
                            CommandName.EXIT.getValue() + ": exit";

                    System.out.println(str);
                    return Response.empty();
                });

        exitCommand.setAction(param -> {
                    isAlive.set(false);
                    return Response.bye();
                });
    }
    private void initCommandGroup() {
        commandGroup.add(newCommand);
        commandGroup.add(saveCommand);
        commandGroup.add(viewCommand);
        commandGroup.add(deleteCommand);
        commandGroup.add(helpCommand);
        commandGroup.add(exitCommand);
    }

}
