package edu.sjsu.cs.addressbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class AdBook {

    private String path;
    private AddressBook.Builder bookBuilder = AddressBook.newBuilder();

    public AdBook(String path, boolean useExisting) throws IOException {
        this.path = path;
        File f = new File(path);
        if (useExisting && f.exists()) {
            FileInputStream fis = new FileInputStream(path);
            bookBuilder.mergeFrom(fis);
        }
    }

    public void addPerson(Person person) {
        bookBuilder.addPerson(person);
    }

    public void addPersons(List<Person> list) {
        for (Person p : list) {
            bookBuilder.addPerson(p);
        }
    }

    void save() throws IOException {
        FileOutputStream fos = new FileOutputStream(path);
        bookBuilder.build().writeTo(fos);
    }

    public List<Person> getPersonList() throws IOException {
        AddressBook book = bookBuilder.build();
        return book.getPersonList();
    }
}
