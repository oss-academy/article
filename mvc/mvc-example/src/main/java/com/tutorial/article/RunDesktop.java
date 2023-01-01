package com.tutorial.article;

import com.tutorial.article.controller.Controller;
import com.tutorial.article.tool.Desktop;
import com.tutorial.article.view.form.FormView;

import javax.swing.*;

public class RunDesktop {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Desktop(Controller.INSTANCE, new FormView());
                } catch (Exception e) {
                    System.out.println("exception message = " + e.getMessage());
                }
            }
        });
    }
}
