package org.ispirer.utils;

import java.io.File;

public class DirectoryTreeViewer {
    public static String getDirectoryTree(File directory, int depth) {
        StringBuilder tree = new StringBuilder();

        tree.append(directory.getAbsolutePath()).append("\n");

        tree.append(getSubtree(directory, depth-1));

        return tree.toString();
    }

    private static String getSubtree(File directory, int depth) {
        StringBuilder tree = new StringBuilder();

        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    tree.append(getWhitespace(depth)).append(file.getName()).append("\n");
                    tree.append(getSubtree(file, depth + 1));
                }
            }
        }
        return tree.toString();
    }

    private static String getWhitespace(int depth) {
        StringBuilder indent = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            indent.append("  ");
        }
        return indent.toString();
    }
}
