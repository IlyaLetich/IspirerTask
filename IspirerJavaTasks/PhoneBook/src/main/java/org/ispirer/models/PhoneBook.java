package org.ispirer.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
@XmlRootElement(name = "phonebook")
public class PhoneBook {
    @XmlElement(name="entry")
    private List<PhoneEntry> phones;

    public List<PhoneEntry> getPhones() {
        if (phones == null) {
            phones = new ArrayList<>();
        }
        return phones;
    }
    public void setEntries(List<PhoneEntry> entries) {
        this.phones = entries;
    }
    public void printBook(){
        for (PhoneEntry item : this.phones)
        {
            System.out.println(item.toString());
        }
    }
}
