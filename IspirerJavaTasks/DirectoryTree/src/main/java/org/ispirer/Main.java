package org.ispirer;

import org.ispirer.frames.AppFrame;
import org.ispirer.utils.DirectoryTreeViewer;

import javax.swing.*;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        String startDirectoryPath = args.length > 0 ? args[0] : ".";
        File startDirectory = new File(startDirectoryPath);

        if (!startDirectory.exists() || !startDirectory.isDirectory()) {
            JOptionPane.showMessageDialog(null, "Directory " + startDirectoryPath + " not found", "Warning", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String resultTree = DirectoryTreeViewer.getDirectoryTree(startDirectory, 2);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AppFrame(resultTree);
            }
        });
    }
}