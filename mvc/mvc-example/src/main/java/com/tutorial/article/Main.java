package com.tutorial.article;

import com.tutorial.article.controller.Controller;
import com.tutorial.article.tool.Console;
import com.tutorial.article.tool.Desktop;
import com.tutorial.article.view.console.ConsoleView;
import com.tutorial.article.view.form.FormView;

import javax.swing.*;

public class Main {

    private static void runConsoleView() {
        var console = new Thread(new Console(Controller.INSTANCE, new ConsoleView()));
        console.start();
    }

    private static void runDesktopView() {
        SwingUtilities.invokeLater(() -> {
            try {
                new Desktop(Controller.INSTANCE, new FormView());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
    }

    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("please specify a view");
            System.exit(1);
        }

        switch (args[0]) {
            case "console" -> runConsoleView();
            case "form" -> runDesktopView();
            default -> System.out.println(
                    "the view is not valid\nconsole and form are the supported view"
            );
        }


    }
}
