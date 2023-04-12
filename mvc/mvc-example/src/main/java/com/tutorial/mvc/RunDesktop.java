package com.tutorial.mvc;

import com.tutorial.mvc.controller.Controller;
import com.tutorial.mvc.tool.Desktop;
import com.tutorial.mvc.view.desktop.DesktopView;

import javax.swing.*;

public class RunDesktop {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Desktop(Controller.INSTANCE, new DesktopView());
                } catch (Exception e) {
                    System.out.println("exception message = " + e.getMessage());
                }
            }
        });
    }
}
