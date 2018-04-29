package de.rieckpil.learning.compareto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CompareToPerson {

    public static void main(String[] args) {

        Person p1 = new Person(24, "Max");
        Person p2 = new Person(35, "Paul");
        Person p3 = new Person(10, "Foo");

        List<Person> persons = new ArrayList<>();
        persons.addAll(Arrays.asList(p1, p2, p3));


        for (Person person : persons) {
            System.out.println(person);
        }

        Collections.sort(persons);
        System.out.println("After sorting:");

        for (Person person : persons) {
            System.out.println(person);
        }

    }
}
