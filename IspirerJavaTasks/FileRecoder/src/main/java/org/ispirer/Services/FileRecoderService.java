package org.ispirer.Services;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;

public class FileRecoderService {
    public static void convertFileEncoding(String inputFile, String outputFile, String currentEncoding, String desiredEncoding) throws IOException {
        File input = new File(inputFile);
        File output = new File(outputFile);

        if (!input.exists()) {
            throw new FileNotFoundException("Input file not found");
        }

        if (!output.exists()) {
            output.createNewFile();
        }

        if (!isEncodingSupported(currentEncoding)) {
            throw new UnsupportedEncodingException("Current encoding not supported");
        }
        if (!isEncodingSupported(desiredEncoding)) {
            throw new UnsupportedEncodingException("Desired encoding not supported");
        }

        try (
             BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(input), currentEncoding));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output), desiredEncoding))
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }
        }
    }

    static boolean isEncodingSupported(String encoding) {
        try {
            Charset.forName(encoding);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
