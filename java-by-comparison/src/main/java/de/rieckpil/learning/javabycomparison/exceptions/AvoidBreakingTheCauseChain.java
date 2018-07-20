package de.rieckpil.learning.javabycomparison.exceptions;

public class AvoidBreakingTheCauseChain {

    public static void main(String[] args) {

        System.out.println(doFooGood("12"));
        System.out.println(doFooGood("1"));
        System.out.println(doFooGood("1s2"));


        System.out.println(doFooBad("12"));
        System.out.println(doFooBad("ASBC"));
        System.out.println(doFooBad("1s2"));
    }

    private static int doFooGood(String number) {

        try {
            int numberAsInt = Integer.parseInt(number);
            return numberAsInt;
        }catch (NumberFormatException e) {
            throw new IllegalArgumentException(String.format("Expected a number, but got '%s'", number), e);
        }

    }

    private static int doFooBad(String number) {

        try {

            int numberAsInt = Integer.parseInt(number);
            return numberAsInt;

        }catch (NumberFormatException e) {
            // throwing excpetion without the cause
            throw new IllegalArgumentException(String.format("Expected a number, but got '%s'", number));
        }

    }
}
