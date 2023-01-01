package com.tutorial.article.tool;

import com.tutorial.article.controller.Controller;
import com.tutorial.article.view.form.FormView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Form extends JFrame {

    private final Controller controller;

    private final FormView view;

    public Form(Controller controller, FormView view) throws HeadlessException {
        this.controller = controller;
        this.view = view;
        bindAction();
        init();
    }

    private void init() {
        JFrame frame = new JFrame("MVC Desktop");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(view);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void bindAction() {
        view.getSaveButton()
                .addActionListener(new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        controller.save(view);
                    }
                });

        view.getViewButton()
                .addActionListener(new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        controller.getAll(view);
                        view.represent();
                    }
                });
    }
}
