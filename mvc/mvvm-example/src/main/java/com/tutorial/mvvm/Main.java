package com.tutorial.mvvm;

import com.tutorial.mvvm.view.console.ConsoleView;
import com.tutorial.mvvm.view.desktop.DesktopView;

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

   private static void runAllView() {
        SwingUtilities.invokeLater(() -> {
            try {
                new DesktopView();
                var console = new Thread(new ConsoleView());
                console.start();
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
            case "all" -> runAllView();
            default -> System.out.println(
                    "the view is not valid\nconsole and desktop are the supported view"
            );
        }


    }
}
