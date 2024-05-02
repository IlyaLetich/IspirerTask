package org.ispirer.services;

import org.ispirer.models.PhoneEntry;

import java.util.ArrayList;
import java.util.List;

public class PhoneBookService {
    public static List<PhoneEntry> getPhoneEntriesBySurname(List<PhoneEntry> phones, String surname){
        List<PhoneEntry> foundEntries = new ArrayList<>();

        for (PhoneEntry item : phones) {
            if (item.getSurname().toLowerCase().contains(surname.toLowerCase())) {
                foundEntries.add(item);
            }
        }

        return foundEntries;
    }
}
