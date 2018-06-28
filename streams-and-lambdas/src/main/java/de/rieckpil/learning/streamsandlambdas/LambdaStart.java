package de.rieckpil.learning.streamsandlambdas;

public class LambdaStart {

    public static void main(String[] args) {

        Thread th = new Thread(() -> System.out.println("in thread"));

        th.start();

        System.out.println("In main");

    }
}
