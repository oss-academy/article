package com.tutorial.mvc.tool;

import com.tutorial.mvc.controller.Controller;
import com.tutorial.mvc.view.form.FormView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Desktop extends JFrame {

    private final Controller controller;

    private final FormView view;

    public Desktop(Controller controller, FormView view) throws HeadlessException {
        this.controller = controller;
        this.view = view;
        bindAction();
        initFrame();
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

    private void initFrame() {
        JFrame frame = new JFrame("MVC Desktop");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(view);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
