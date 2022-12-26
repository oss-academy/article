package com.tutorial.article;

import com.tutorial.article.controller.Controller;
import com.tutorial.article.view.Console;
import com.tutorial.article.view.ConsoleView;
import com.tutorial.article.view.Desktop;
import com.tutorial.article.view.DesktopView;

import javax.swing.*;

public class Main {

    private static void runConsoleView() {
        var console = new Thread(new Console(Controller.INSTANCE, new ConsoleView()));
        console.start();
    }

    private static void runDesktopView() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Desktop(Controller.INSTANCE, new DesktopView());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        runDesktopView();
    }
}
