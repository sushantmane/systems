package edu.sjsu.cs.addressbook;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static List<Person> getPersonList() {
        String[][] arr = {
                {"1", "Hector", "hector@sjsu.edu", "669-219-0001", "HOME"},
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

    void readDemo(String path) throws IOException {
        AdBook adBook = new AdBook(path, true);
        List<Person> list = adBook.getPersonList();
        for (Person person : list) {
            System.out.println(person);
        }
    }

    void writeDemo(String path) throws IOException {
        AdBook adBook = new AdBook(path, false);
        adBook.addPersons(getPersonList());
        adBook.save();
    }

    public static void main(String[] args) throws IOException {
        String path = "/tmp/adbook.pbd";
        Main main = new Main();
        main.writeDemo(path);
        main.readDemo(path);
    }
}
