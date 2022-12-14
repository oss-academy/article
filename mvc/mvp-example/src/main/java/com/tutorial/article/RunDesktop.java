package com.tutorial.article;

import com.tutorial.article.view.form.FormView;

import javax.swing.*;

public class RunDesktop {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    new FormView();
                } catch (Exception e) {
                    System.out.println("exception message = " + e.getMessage());
                }
            }
        });
    }
}
