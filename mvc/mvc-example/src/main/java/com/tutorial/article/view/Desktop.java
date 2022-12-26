package com.tutorial.article.view;

import com.tutorial.article.controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Desktop extends JFrame {
    private final Controller controller;

    private final DesktopView view;

    public Desktop(Controller controller, DesktopView view) throws HeadlessException {
        this.controller = controller;
        this.view = view;
        init();
        initAction();
    }

    private void init() {
        JFrame frame = new JFrame("MVC Desktop");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(view);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void initAction() {
        view.setSaveAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.models(view.readForm());
                controller.save(view);
            }
        });

        view.setShowButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.getAll(view);
                view.view();
            }
        });
    }
}
