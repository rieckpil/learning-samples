package de.rieckpil.learning.streamsandlambdas;

import java.util.Arrays;
import java.util.List;

public class LambdaStart {

    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        numbers.forEach(integer -> System.out.println(integer));

    }


    public void lambdaForInnerAnonymousClasses() {
        Thread th = new Thread(() -> System.out.println("in thread"));
        th.start();
        System.out.println("In main");
    }
}
