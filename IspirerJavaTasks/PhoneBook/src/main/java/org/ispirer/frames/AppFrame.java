package org.ispirer.frames;
import org.ispirer.models.PhoneBook;
import org.ispirer.models.PhoneEntry;
import org.ispirer.services.PhoneBookService;
import org.ispirer.utils.PhoneBookFromXmlReader;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AppFrame extends JFrame {
    JPanel mainPanel = new JPanel(new GridLayout(1, 2, 10, 10));
    JPanel leftPanel = new JPanel();
    JPanel rightPanel = new JPanel(new BorderLayout());
    JTextField pathFileTextField = new JTextField(10);
    JButton getDataFromFileButton = new JButton("Get Phone Book");
    JTextField surnameSearchTextField = new JTextField(10);
    DefaultListModel<PhoneEntry> listPhones = new DefaultListModel<>();
    JList<PhoneEntry> list = new JList<>(listPhones);
    JScrollPane scrollPane = new JScrollPane(list);

    PhoneBook phoneBookDefault;

    public AppFrame() {
        setTitle("Phone Book");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        leftPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JPanel pathPanel = new JPanel(new BorderLayout());
        pathPanel.add(new JLabel("Path to File:"), BorderLayout.WEST);
        pathPanel.add(pathFileTextField, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(getDataFromFileButton);

        leftPanel.add(pathPanel, BorderLayout.NORTH);
        leftPanel.add(buttonPanel, BorderLayout.CENTER);

        rightPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JPanel textPanel = new JPanel(new BorderLayout());
        textPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        textPanel.add(new JLabel("Surname:"), BorderLayout.WEST);
        textPanel.add(surnameSearchTextField, BorderLayout.CENTER);

        rightPanel.add(textPanel, BorderLayout.NORTH);
        rightPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);

        add(mainPanel);

        this.getDataFromFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    phoneBookDefault = PhoneBookFromXmlReader.readFromXml(pathFileTextField.getText());

                    listPhones.clear();
                    surnameSearchTextField.setText("");
                    listPhones.addAll(phoneBookDefault.getPhones());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(
                            AppFrame.this,
                            ex.getMessage(),
                            "Warning",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        this.surnameSearchTextField.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                String surname = surnameSearchTextField.getText();

                listPhones.clear();

                if(surname.equalsIgnoreCase("")){
                    listPhones.addAll(phoneBookDefault.getPhones());
                    return;
                }

                List<PhoneEntry> foundPhones = PhoneBookService.getPhoneEntriesBySurname(
                        phoneBookDefault.getPhones(),
                        surname);

                listPhones.addAll(foundPhones);
            }
        });

        setVisible(true);
    }
}
