package com.tutorial.article;

import com.tutorial.article.controller.Controller;
import com.tutorial.article.tool.Console;
import com.tutorial.article.view.console.ConsoleView;

public class RunConsole {

    public static void main(String[] args) {
        var console = new Thread(new Console(Controller.INSTANCE, new ConsoleView()));
        console.start();
    }
}
