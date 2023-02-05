package com.tutorial.mvp.view.form;

import com.tutorial.mvp.controller.Controller;
import com.tutorial.mvp.model.Model;
import com.tutorial.mvp.view.InputModel;
import com.tutorial.mvp.view.View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.util.stream.IntStream;

public final class FormView extends JFrame implements View<Model, InputModel> {

    private Model[] models = new Model[0];

    private final Controller controller = Controller.INSTANCE;

    private final JTextField numberInput = new JTextField(20);

    private final JLabel numberLabel = new JLabel("Number");

    private final JTextField textInput = new JTextField(20);

    private final JLabel textLabel = new JLabel("Text");

    private final JButton saveButton = new JButton("Save");

    private final JButton viewButton = new JButton("View");

    private final JTable table = new JTable(new DefaultTableModel(new Object[]{"ID","Text", "Number"}, 10) {
    });

    private final JScrollPane tableScroll = new JScrollPane(table);

    public FormView() {
        setTitle("MVP Desktop");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        bindAction();
        add(design());
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public Model[] getModel() {
        return models;
    }

    @Override
    public void setModel(Model... models) {
        this.models = models;
    }

    @Override
    public InputModel getInput() {
        return new InputModel(textInput.getText().trim(), numberInput.getText().trim());
    }

    @Override
    public void setInput() {
        textInput.setText("");
        numberInput.setText("");
    }

    @Override
    public void represent() {
        table.removeAll();
        IntStream.range(0, models.length)
                .forEach(row -> {
                    table.setValueAt(models[row].id(), row, 0);
                    table.setValueAt(models[row].text(), row, 1);
                    table.setValueAt(models[row].number(), row, 2);
                });
    }
    private void bindAction() {
        saveButton.addActionListener(new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        controller.save(getInput());
                    }
                });

        viewButton.addActionListener(new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Model[] result = controller.getAll();
                        setModel(result);
                        represent();
                    }
                });
    }

    private JPanel design() {
        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(textLabel)
                                        .addComponent(numberLabel))
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(textInput)
                                        .addComponent(numberInput))
                        )
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(saveButton)
                                .addComponent(viewButton))
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(tableScroll))
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup()
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(textLabel)
                                        .addComponent(numberLabel))
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(textInput)
                                        .addComponent(numberInput)))
                        .addGroup(layout.createParallelGroup()
                                .addComponent(saveButton)
                                .addComponent(viewButton))
                        .addGroup(layout.createParallelGroup()
                                .addComponent(tableScroll)));

        return panel;
    }

}
