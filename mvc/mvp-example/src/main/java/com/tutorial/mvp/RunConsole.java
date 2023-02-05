package com.tutorial.mvp;

import com.tutorial.mvp.view.console.ConsoleView;

public class RunConsole {

    public static void main(String[] args) {
        var console = new Thread(new ConsoleView());
        console.start();
    }
}
