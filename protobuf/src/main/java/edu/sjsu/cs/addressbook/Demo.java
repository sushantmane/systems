package edu.sjsu.cs.addressbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Demo {

    List<Person> createPersonList() {
        String[][] arr = {
                {"1", "Sushant", "sushant@sjsu.edu", "669-219-0001", "HOME"},
                {"2", "Alice", "alice@sjsu.edu", "669-219-0002", "MOBILE"},
                {"3", "Joe", "joe@sjsu.edu", "669-219-0003", "HOME"},
                {"4", "Rose", "rose@sjsu.edu", "669-219-0004", "WORK"},
                {"5", "Cinderella", "cinderella@sjsu.edu", "669-219-0005", "MOBILE"},
        };
        List<Person> people = new ArrayList<>();
        for (String[] person : arr) {
            Person p = Person.newBuilder()
                    .setId(Integer.parseInt(person[0]))
                    .setName(person[1])
                    .setEmail(person[2])
                    .addPhones(Person.PhoneNumber.newBuilder()
                            .setNumber(person[3])
                            .setType(Person.PhoneType.valueOf(person[4]))
                    )
                    .build();
            people.add(p);
        }
        return people;
    }

    AddressBook createAddressBook(List<Person> people) {
        AddressBook.Builder ab = AddressBook.newBuilder();
        for (Person person : people) {
            ab.addPerson(person);
        }
        return ab.build();
    }

    void persist(String fname, AddressBook book) throws IOException {
        FileOutputStream fos = new FileOutputStream(fname);
        book.writeTo(fos);
    }

    void read(String fname) throws IOException {
        FileInputStream fis = new FileInputStream(fname);
        AddressBook ab = AddressBook.parseFrom(fis);
        for (Person p : ab.getPersonList()) {
            System.out.println(p.toString());
        }

        AddressBook.Builder abBuilder = AddressBook.newBuilder();
        abBuilder.mergeFrom(new FileInputStream(fname));

    }

    public static void main(String[] args) throws IOException {
        String fname = "/Users/sushantmane/systems/protobuf/src/main/resources/tmp.data";
        Demo demo = new Demo();
        // AddressBook ab = demo.createAddressBook(demo.createPersonList());
        //demo.persist(fname, ab);
        demo.read(fname);
    }
}
