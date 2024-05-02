package org.ispirer.frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

public class AppFrame extends JFrame {
    private final JLabel label = new JLabel("Label");
    private final JButton button = new JButton("Button");;
    private ResourceBundle dataTexts = ResourceBundle.getBundle("dataTexts", Locale.getDefault());;

    public AppFrame() {
        setTitle("Simple multilanguage application");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new GridLayout(1, 2));

        JPanel leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setBackground(Color.GREEN);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 0, 0);
        leftPanel.add(button, gbc);

        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBackground(Color.WHITE);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 0, 0);
        rightPanel.add(label, gbc);

        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);

        add(mainPanel, BorderLayout.CENTER);

        this.button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchLanguage();
            }
        });

        switchLanguage();

        setVisible(true);
    }

    private void switchLanguage() {
        if (Locale.getDefault().equals(Locale.ENGLISH)) {
            Locale.setDefault(new Locale("ru", "RU"));
        } else {
            Locale.setDefault(Locale.ENGLISH);
        }
        dataTexts = ResourceBundle.getBundle("dataTexts", Locale.getDefault());
        button.setText(dataTexts.getString("button_text"));
        label.setText(dataTexts.getString("label_text"));
    }
}
