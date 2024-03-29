package com.tutorial.mvp;

import com.tutorial.mvp.model.Model;
import com.tutorial.mvp.view.InputModel;
import com.tutorial.mvp.view.View;

import static com.tutorial.mvp.util.ArrayUtils.convertToString;

public final class TestConsoleView implements View<Model, InputModel> {

    private String numberInput;

    private String textInput;

    private Model[] models = new Model[0];

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
        return new InputModel(textInput, numberInput);
    }

    @Override
    public void setInput() {
        System.out.print("set number =1");
        numberInput = "1";

        System.out.print("set text =fake");
        textInput = "fake";

        System.out.println();
    }

    @Override
    public void represent() {
        System.out.print(convertToString(models, "models"));
    }
}
