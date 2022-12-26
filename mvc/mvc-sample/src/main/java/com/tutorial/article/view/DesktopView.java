package com.tutorial.article.view;

import com.tutorial.article.model.Model;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.stream.IntStream;

public final class DesktopView extends JPanel implements View<Model> {

    public static final Object[] TABLE_HEADER = {"Text", "Number"};

    private Model[] models = new Model[0];

    private JTextField text, number;

    private JLabel textLabel, numberLabel;

    private JButton saveButton, showButton;

    private JTable table;

    private JScrollPane tableScroll;

    public DesktopView() {
        init();
    }

    @Override
    public Model[] models() {
        return models;
    }

    @Override
    public void models(Model... models) {
        this.models = models;
    }

    private void init() {
        text = new JTextField(20);
        text.setText("test");
        textLabel = new JLabel("Text");

        number = new JTextField(20);
        number.setText("1");
        numberLabel = new JLabel("Number");

        saveButton = new JButton("Save");
        showButton = new JButton("Show");

        table = new JTable(new DefaultTableModel(TABLE_HEADER, 10) {
        });

        tableScroll = new JScrollPane(table);
        tableScroll.setPreferredSize(new Dimension(700, 182));
        tableScroll.setBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createEtchedBorder(),
                        "Models",
                        TitledBorder.CENTER,
                        TitledBorder.TOP
                )
        );

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(textLabel)
                                        .addComponent(numberLabel)
                                )
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(text)
                                        .addComponent(number)
                                )
                        )
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(saveButton)
                                .addComponent(showButton)
                        )
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(tableScroll)
                        )
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup()
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(textLabel)
                                        .addComponent(numberLabel)
                                )
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(text)
                                        .addComponent(number)
                                )
                        )
                        .addGroup(layout.createParallelGroup()
                                .addComponent(saveButton)
                                .addComponent(showButton)
                        )
                        .addGroup(layout.createParallelGroup()
                                .addComponent(tableScroll)
                        )
        );

    }

    @Override
    public void view() {
        table.removeAll();
        IntStream.range(0, models.length)
                .forEach(row -> {
                    table.setValueAt(models[row].text(), row, 0);
                    table.setValueAt(models[row].number(), row, 1);
                });
    }

    public Model readForm() {
        return new Model(Integer.parseInt(number.getText().trim()), text.getText().trim());
    }

    public void setSaveAction(ActionListener listener) {
        this.saveButton.addActionListener(listener);
    }

    public void setShowButton(ActionListener listener) {
        this.showButton.addActionListener(listener);
    }
}
