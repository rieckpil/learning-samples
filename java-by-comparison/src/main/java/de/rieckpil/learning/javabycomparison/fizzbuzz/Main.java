package de.rieckpil.learning.javabycomparison.fizzbuzz;

public class Main {

    public static void main(String[] args) {

        FizzBuzz fizzBuzz = new ConsoleBasedFizzBuzz();

        fizzBuzz.print(1, Integer.parseInt(args[0]));

    }
}
