package org.ispirer.frames;

import org.ispirer.models.DebitCard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppFrame extends JFrame {
    JPanel mainPanel = new JPanel(new GridLayout(1, 2, 10, 10));
    JPanel leftPanel = new JPanel(new BorderLayout());
    JPanel rightPanel = new JPanel(new BorderLayout());
    JTextField fioUser = new JTextField(10);
    JPanel durationCard = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JRadioButton fiveYears = new JRadioButton("5");
    JRadioButton tenYears = new JRadioButton("10");
    JButton button = new JButton("Get debit card");
    JLabel counOfInstances = new JLabel("Count: " + DebitCard.getCountOfInstance());
    DefaultListModel<DebitCard> listDebitCards = new DefaultListModel<>();
    JList<DebitCard> list = new JList<>(listDebitCards);
    JScrollPane scrollPane = new JScrollPane(list);

    public AppFrame() {
        setTitle("Instance Counter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        leftPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        leftPanel.add(fioUser, BorderLayout.NORTH);

        durationCard.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        ButtonGroup groupYears = new ButtonGroup();
        groupYears.add(fiveYears);
        groupYears.add(tenYears);

        durationCard.add(fiveYears);
        durationCard.add(tenYears);

        leftPanel.add(durationCard, BorderLayout.CENTER);
        leftPanel.add(button, BorderLayout.SOUTH);

        rightPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        rightPanel.add(scrollPane, BorderLayout.CENTER);
        rightPanel.add(counOfInstances, BorderLayout.NORTH);

        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);

        add(mainPanel);

        this.button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fio = fioUser.getText();

                if (fio.isEmpty()) {
                    JOptionPane.showMessageDialog(
                            AppFrame.this,
                            "Please enter your full name",
                            "Warning",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int years = 0;
                if (fiveYears.isSelected()) {
                    years = 5;
                }
                if (tenYears.isSelected()) {
                    years = 10;
                }
                if(years <= 0) {
                    JOptionPane.showMessageDialog(
                            AppFrame.this,
                            "Please enter duration",
                            "Warning",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                DebitCard newCard = new DebitCard(fio, years);
                listDebitCards.addElement(newCard);

                counOfInstances.setText("Count: " + DebitCard.getCountOfInstance());
            }
        });
        setVisible(true);
    }
}
