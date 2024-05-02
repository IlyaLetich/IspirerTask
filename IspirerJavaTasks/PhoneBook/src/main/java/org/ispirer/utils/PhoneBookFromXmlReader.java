package org.ispirer.utils;

import org.ispirer.models.PhoneBook;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.stream.Collectors;

public class PhoneBookFromXmlReader {
    public static PhoneBook readFromXml(String path) throws JAXBException, FileNotFoundException {
        File file = new File(path);
        if (!file.exists()) {
            throw new FileNotFoundException("File " + path + " not found");
        }

        String extension = getFileExtension(file);
        if (!extension.equalsIgnoreCase("xml")) {
            throw new IllegalArgumentException("Format is not XML");
        }

        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        StringReader reader = new StringReader(bufferedReader.lines().collect(Collectors.joining()));

        Unmarshaller unmarshaller = JAXBContext.newInstance(PhoneBook.class).createUnmarshaller();

        return (PhoneBook) unmarshaller.unmarshal(reader);
    }

    private static String getFileExtension(File file) {
        String fileName = file.getName();
        int lastIndex = fileName.lastIndexOf('.');

        if (lastIndex == -1 || lastIndex == fileName.length() - 1) {
            return "";
        }
        return fileName.substring(lastIndex + 1);
    }
}
