package org.ispirer;


import org.ispirer.frames.AppFrame;
import org.ispirer.models.CustomList;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AppFrame();
            }
        });
    }
}