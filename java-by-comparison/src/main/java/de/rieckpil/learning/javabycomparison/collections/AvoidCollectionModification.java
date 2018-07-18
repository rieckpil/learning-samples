package de.rieckpil.learning.javabycomparison.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AvoidCollectionModification {

    public static void main(String[] args) {

        List<String> names = new ArrayList<>();
        names.add("Tom");
        names.add("Mike");
        names.add("Philip");
        names.add("Andy");
        names.add("Jim");
        names.add("Richard");

        System.out.println("length of list is: " + names.size());

        // throws ConcurrentModificationException
        /*for (String name : names) {

            if(name.length() > 3) {
                names.remove(name);
            }

        }*/

        Iterator<String> iterator = names.iterator();

        while (iterator.hasNext()) {
            if (iterator.next().length() > 3) {
                iterator.remove();
            }
        }

        System.out.println("length of list is: " + names.size());


    }
}
