package org.ispirer.frames;

import org.ispirer.models.CustomList;
import org.ispirer.models.DebitCard;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
    JButton getDebitCardButton = new JButton("Get debit card");
    JButton deleteDebitCardButton = new JButton("Delete debit card");
    DefaultListModel<DebitCard> listDebitCards = new DefaultListModel<>();
    JList<DebitCard> list = new JList<>(listDebitCards);
    JScrollPane scrollPane = new JScrollPane(list);

    CustomList<DebitCard> debitCardCustomList = new CustomList<>();
    DebitCard selectedDebitCard = null;

    void setSelectedDebitCard(DebitCard debitCard){
        this.selectedDebitCard = debitCard;
    }

    public AppFrame() {
        setTitle("List Editor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        debitCardCustomList.addChangeHandler(new CustomList.ChangeHandler<DebitCard>() {
            public void onChange(String nameEvent, DebitCard changedItem) {
                if (nameEvent.equals("add")) {
                    listDebitCards.addElement(changedItem);
                    JOptionPane.showMessageDialog(
                            AppFrame.this,
                            "Debit card successfully added!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                } else if (nameEvent.equals("remove")) {
                    listDebitCards.removeElement(changedItem);
                    JOptionPane.showMessageDialog(
                            AppFrame.this,
                            "Debit card successfully removed!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });


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

        JPanel buttonPanel = new JPanel(new GridLayout(2, 1));
        buttonPanel.add(getDebitCardButton);
        buttonPanel.add(deleteDebitCardButton);

        leftPanel.add(buttonPanel, BorderLayout.SOUTH);

        rightPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        rightPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);

        add(mainPanel);

        this.getDebitCardButton.addActionListener(new ActionListener() {
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
                if (years <= 0) {
                    JOptionPane.showMessageDialog(
                            AppFrame.this,
                            "Please select duration",
                            "Warning",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                DebitCard newCard = new DebitCard(fio, years);

                debitCardCustomList.add(newCard);
            }
        });

        this.deleteDebitCardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = list.getSelectedIndex();
                if (selectedIndex != -1) {
                    debitCardCustomList.remove(selectedIndex);
                } else {
                    JOptionPane.showMessageDialog(AppFrame.this, "Please select debit card", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        });


        this.list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    DebitCard selectedCard = list.getSelectedValue();
                    if (selectedCard != null) {
                        setSelectedDebitCard(selectedCard);
                    }
                }
            }
        });

        setVisible(true);
    }
}