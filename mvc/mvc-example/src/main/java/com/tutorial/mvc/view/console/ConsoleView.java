package com.tutorial.mvc.view.console;

import com.tutorial.mvc.model.Model;
import com.tutorial.mvc.view.InputModel;
import com.tutorial.mvc.view.View;
import com.tutorial.mvc.view.console.command.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.tutorial.mvc.util.ArrayUtils.convertToString;

public final class ConsoleView implements View<Model, InputModel> {

    private final Scanner scanner = new Scanner(System.in);

    private Model[] models = new Model[0];

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

    private void initCommandGroup() {
        commandGroup.add(newCommand);
        commandGroup.add(saveCommand);
        commandGroup.add(viewCommand);
        commandGroup.add(deleteCommand);
        commandGroup.add(helpCommand);
        commandGroup.add(exitCommand);
    }

    public List<Command> getCommandGroup() {
        return commandGroup;
    }

    public Command getNewCommand() {
        return newCommand;
    }

    public Command getSaveCommand() {
        return saveCommand;
    }

    public Command getViewCommand() {
        return viewCommand;
    }

    public Command getDeleteCommand() {
        return deleteCommand;
    }

    public Command getHelpCommand() {
        return helpCommand;
    }

    public Command getExitCommand() {
        return exitCommand;
    }
}
