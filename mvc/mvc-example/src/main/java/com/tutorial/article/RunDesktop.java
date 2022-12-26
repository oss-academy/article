package com.tutorial.article;

import com.tutorial.article.controller.Controller;
import com.tutorial.article.view.Desktop;
import com.tutorial.article.view.DesktopView;

import javax.swing.*;

public class RunDesktop {

    public static void main(String[] args) {
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
}
