package com.tutorial.mvp;

import com.tutorial.mvp.view.console.ConsoleView;
import com.tutorial.mvp.view.desktop.DesktopView;

import javax.swing.*;

public class Main {

    private static void runConsoleView() {
        var console = new Thread(new ConsoleView());
        console.start();
    }

    private static void runDesktopView() {
        SwingUtilities.invokeLater(() -> {
            try {
                new DesktopView();
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
            case "desktop" -> runDesktopView();
            default -> System.out.println(
                    "the view is not valid\nconsole and desktop are the supported view"
            );
        }


    }
}
