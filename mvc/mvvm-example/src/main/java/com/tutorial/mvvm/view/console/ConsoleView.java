package com.tutorial.mvvm.view.console;

import com.tutorial.mvvm.model.Model;
import com.tutorial.mvvm.view.InputModel;
import com.tutorial.mvvm.view.View;
import com.tutorial.mvvm.view.console.command.*;
import com.tutorial.mvvm.viewmodel.ViewModel;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

import static com.tutorial.mvvm.util.ArrayUtils.convertToString;
import static com.tutorial.mvvm.view.console.command.Response.*;
import static com.tutorial.mvvm.view.console.command.CommandName.*;
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

    private final ViewModel viewModel = ViewModel.INSTANCE;

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
        viewModel.addView(this);
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
        System.out.print(convertToString("models", models));
    }

    @Override
    public void update(Model[] models) {
        setModel(models);
        represent();
    }

    @Override
    public void bindAction() {
        newCommand.setAction(param -> {
            setInput();
            return ok();
        });

        saveCommand.setAction(param -> {
            viewModel.save(getInput());
            return ok();
        });

        viewCommand.setAction(param -> {
            if (param.containsKey("id")) {
                Optional<Model> result = viewModel.getById(Integer.parseInt(param.get("id").toString()));
                result.ifPresent(this::setModel);
            } else {
                Model[] result = viewModel.getAll();
                setModel(result);
            }
            represent();
            return ok();
        });

        deleteCommand.setAction(param -> {
            if (param.containsKey("id")) {
                viewModel.deleteById(Integer.parseInt(param.get("id").toString()));
            } else {
                System.out.println("delete all is not allowed");
            }
            return ok();
        });

        helpCommand.setAction(param -> {
            var str = HELP.getValue() + ": show help\n" +
                    VIEW.getValue() + " [id=number]: view persisted models\n" +
                    NEW.getValue() + ": initial new model\n" +
                    SAVE.getValue() + ": save new model to storage\n" +
                    DELETE.getValue() + " [id=number]: delete persisted models from storage\n" +
                    EXIT.getValue() + ": exit";

            System.out.println(str);
            return empty();
        });

        exitCommand.setAction(param -> {
            isAlive.set(false);
            return bye();
        });
    }

    @Override
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

    private void initCommandGroup() {
        commandGroup.add(newCommand);
        commandGroup.add(saveCommand);
        commandGroup.add(viewCommand);
        commandGroup.add(deleteCommand);
        commandGroup.add(helpCommand);
        commandGroup.add(exitCommand);
    }

}
