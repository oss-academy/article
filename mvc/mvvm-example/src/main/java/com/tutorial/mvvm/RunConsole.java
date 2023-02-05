package com.tutorial.mvvm;

import com.tutorial.mvvm.view.console.ConsoleView;

public class RunConsole {

    public static void main(String[] args) {
        var console = new Thread(new ConsoleView());
        console.start();
    }
}
