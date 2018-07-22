package de.rieckpil.learning.javabycomparison.lambdas;

public class InnerClass {

    public static void main(String[] args) {

        Runnable runnable = () -> System.out.println("Hello World");

        runnable.run();

    }
}
