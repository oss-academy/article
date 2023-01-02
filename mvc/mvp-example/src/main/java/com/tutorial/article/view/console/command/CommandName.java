package com.tutorial.article.view.console.command;

public enum CommandName {
    NEW("new"),
    SAVE("save"),
    CLEAR("clear"),
    VIEW("view"),
    HELP("help"),
    DELETE("delete"),
    EXIT("exit"),
    ;

    private final String value;

    CommandName(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}