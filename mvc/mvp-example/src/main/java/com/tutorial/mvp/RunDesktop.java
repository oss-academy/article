package com.tutorial.mvp;

import com.tutorial.mvp.view.desktop.DesktopView;

import javax.swing.*;

public class RunDesktop {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    new DesktopView();
                } catch (Exception e) {
                    System.out.println("exception message = " + e.getMessage());
                }
            }
        });
    }
}
