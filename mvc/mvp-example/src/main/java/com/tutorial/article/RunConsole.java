package com.tutorial.article;

import com.tutorial.article.view.console.ConsoleView;

public class RunConsole {

    public static void main(String[] args) {
        var console = new Thread(new ConsoleView());
        console.start();
    }
}
