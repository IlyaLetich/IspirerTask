package org.ispirer;

import org.ispirer.Services.FileRecoderService;

import javax.swing.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        if (args.length != 4) {
            JOptionPane.showMessageDialog(
                    null,
                    "java -jar FileEncoder <input_file> <current_encoding> <output_file> <desired_encoding>",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        String inputFile = args[0];
        String outputFile = args[1];
        String currentEncoding = args[2];
        String desiredEncoding = args[3];

        try {
            FileRecoderService.convertFileEncoding(inputFile, outputFile, currentEncoding, desiredEncoding);
            JOptionPane.showMessageDialog(
                    null,
                    "File successfully recoded",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        catch (Exception ex){
            JOptionPane.showMessageDialog(
                    null,
                    ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

}