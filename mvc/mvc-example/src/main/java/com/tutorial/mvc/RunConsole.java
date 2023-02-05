package com.tutorial.mvc;

import com.tutorial.mvc.controller.Controller;
import com.tutorial.mvc.tool.Console;
import com.tutorial.mvc.view.console.ConsoleView;

public class RunConsole {

    public static void main(String[] args) {
        var console = new Thread(new Console(Controller.INSTANCE, new ConsoleView()));
        console.start();
    }
}
