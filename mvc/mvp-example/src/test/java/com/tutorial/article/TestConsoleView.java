package com.tutorial.article;

import com.tutorial.article.model.Model;
import com.tutorial.article.view.InputModel;
import com.tutorial.article.view.View;

import static com.tutorial.article.util.ArrayUtils.convertToString;

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
        System.out.print("set number =");
        numberInput = "1";

        System.out.print("set text =");
        textInput = "fake";
    }

    @Override
    public void represent() {
        System.out.print(convertToString(models, "persisted models"));
    }
}
