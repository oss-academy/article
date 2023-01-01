package com.tutorial.article;

import com.tutorial.article.controller.Controller;
import com.tutorial.article.tool.Form;
import com.tutorial.article.view.form.FormView;

import javax.swing.*;

public class RunDesktop {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Form(Controller.INSTANCE, new FormView());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
