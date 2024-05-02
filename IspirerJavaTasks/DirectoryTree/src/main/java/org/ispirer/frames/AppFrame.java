package org.ispirer.frames;

import javax.swing.*;

public class AppFrame extends JFrame {
    public AppFrame(String directoryTree){
        setTitle("Directory Tree");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane);

        textArea.setText(directoryTree);

        setVisible(true);
    }
}
