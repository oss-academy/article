package com.tutorial.article;

import com.tutorial.article.controller.Controller;
import com.tutorial.article.view.Console;
import com.tutorial.article.view.ConsoleView;

public class Main {

    public static void main(String[] args) {
        var console = new Thread(new Console(Controller.INSTANCE, new ConsoleView()));
        console.start();
    }
}
