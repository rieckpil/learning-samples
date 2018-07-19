package de.rieckpil.learning.javabycomparison.codestyle;

import java.time.LocalDate;

public class FormattingStrings {

    public static void main(String[] args) {

        String result = String.format("%S: [%tm-%<te-%<tY](Day %d)> %s%n", "rieckpil", LocalDate.now(), 42, "Hello" +
                " World");

        System.out.println(result);

    }
}
