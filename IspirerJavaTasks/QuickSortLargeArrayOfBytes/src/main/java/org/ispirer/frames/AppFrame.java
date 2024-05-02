package org.ispirer.frames;

import org.ispirer.utils.QuickSorter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class AppFrame extends JFrame {
    JPanel mainPanel = new JPanel(new GridLayout(5, 1));
    JTextField pathFromFile = new JTextField();
    JTextField pathToFile = new JTextField();

    JButton sortButton = new JButton("Sort");
    public AppFrame(){
        setTitle("Quick Sorter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        mainPanel.add(new JLabel("From:"));
        mainPanel.add(pathFromFile);
        mainPanel.add(new JLabel("To:"));
        mainPanel.add(pathToFile);
        mainPanel.add(sortButton);

        add(mainPanel);

        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    File fromFile = new File(pathFromFile.getText());
                    if (!fromFile.exists()) {
                        throw new FileNotFoundException("File " + fromFile + " not found");
                    }


                    FileInputStream inputStream = new FileInputStream(fromFile);
                    byte[] bytes = new byte[inputStream.available()];
                    inputStream.read(bytes);
                    inputStream.close();

                    QuickSorter.quickSortArrayOfBytes(bytes,0, bytes.length - 1);

                    if(pathToFile.getText().equals("")){
                        JOptionPane.showMessageDialog(
                                AppFrame.this,
                                "Please enter path output file",
                                "Warning",
                                JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    File toFile = new File(pathToFile.getText());

                    FileOutputStream outputStream = new FileOutputStream(toFile);
                    outputStream.write(bytes);
                    outputStream.close();

                    pathFromFile.setText("");
                    pathToFile.setText("");

                    JOptionPane.showMessageDialog(
                            AppFrame.this,
                            "The file has been successfully sorted",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(
                            AppFrame.this,
                            ex.getMessage(),
                            "Warning",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });


        setVisible(true);
    }
}
