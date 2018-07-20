package de.rieckpil.learning.javabycomparison.exceptions;

public class AwaysCheckBeforeYouCast {

    public static void main(String[] args) {
        castToObject(12);
        castToObject("1212sasd");
        castToObject(3013L);
    }

    private static void castToObject(Object o) {

        if (o instanceof String) {
            String parsedString = (String) o;
            System.out.println(String.format("Parsed '%s' to String", parsedString));
        } else {
            System.out.println("unable to cast to String");
        }


    }
}
