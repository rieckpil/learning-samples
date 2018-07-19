package de.rieckpil.learning.javabycomparison.codestyle;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class CollectionsFrequency {

    private static List<String> messages = List.of("Tom", "42", "Hello World", "42", "Mike", "Paul", "", "2");

    public static void main(String[] args) throws Exception {

        System.out.println(getQuantity("Michael"));

        Thread.sleep(300);

        System.out.println(getQuantity("42"));

        Thread.sleep(300);

        System.out.println(getQuantity(""));

        Thread.sleep(300);

        System.out.println(getQuantity("World"));

        Thread.sleep(300);

        System.out.println(getQuantity(null));

    }

    private static int getQuantity(String msg) {

        Objects.requireNonNull(msg, "message must not be null");

        return Collections.frequency(messages, msg);

    }
}
