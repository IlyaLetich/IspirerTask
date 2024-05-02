package org.ispirer.frames;

import org.ispirer.exceptions.DivideByZeroComplexNumberException;
import org.ispirer.models.ComplexNumber;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppFrame extends JFrame {
    JPanel mainPanel = new JPanel(new GridLayout(8, 1));
    JTextField complexNumberOneTextField = new JTextField();
    JTextField complexNumberOneTextTwo = new JTextField();

    JButton getResultButton = new JButton("Get result");

    JComboBox typeActionComboBox = new JComboBox(new String[]{"+", "-", "*", "/"});
    JLabel resultLabel = new JLabel("Result: ");
    public AppFrame(){
        setTitle("Complex Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        mainPanel.add(new JLabel("Complex number 1:"));
        mainPanel.add(complexNumberOneTextField);
        mainPanel.add(new JLabel("Complex number 2:"));
        mainPanel.add(complexNumberOneTextTwo);
        mainPanel.add(new JLabel("Select action:"));
        mainPanel.add(typeActionComboBox);
        mainPanel.add(getResultButton);
        mainPanel.add(resultLabel);

        add(mainPanel);

        getResultButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ComplexNumber complexNumber1 = null;
                ComplexNumber complexNumber2 = null;
                try {
                    complexNumber1 = ComplexNumber.parseComplexNumber(complexNumberOneTextField.getText());
                    complexNumber2 = ComplexNumber.parseComplexNumber(complexNumberOneTextTwo.getText());
                }
                catch (IllegalArgumentException ex){
                    JOptionPane.showMessageDialog(AppFrame.this, ex.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                String selectedOperation = (String) typeActionComboBox.getSelectedItem();

                switch (selectedOperation){
                    case "+":
                        resultLabel.setText("Result: " + complexNumber1.add(complexNumber2));
                        break;
                    case "-":
                        resultLabel.setText("Result: " + complexNumber1.subtract(complexNumber2));
                        break;
                    case "*":
                        resultLabel.setText("Result: " + complexNumber1.multiply(complexNumber2));
                        break;
                    case "/":
                        try {
                            resultLabel.setText("Result: " + complexNumber1.divide(complexNumber2));
                        } catch (DivideByZeroComplexNumberException ex) {
                            JOptionPane.showMessageDialog(AppFrame.this, "Cannot divide by zero", "Error", JOptionPane.ERROR_MESSAGE);
                            throw new RuntimeException(ex);
                        }
                        break;
                    default:
                        JOptionPane.showMessageDialog(AppFrame.this, "The action is not selected", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        });


        setVisible(true);
    }
}
