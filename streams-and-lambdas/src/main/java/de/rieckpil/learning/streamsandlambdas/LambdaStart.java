package de.rieckpil.learning.streamsandlambdas;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LambdaStart {

    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        List<Integer> newList =
                numbers.stream()
                    .filter(e -> e % 2 == 0)
                    .map(e -> e * 2)
                    .collect(Collectors.toList());

        System.out.println(newList);


    }

    public static void parallelStreams() {

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        TimeIt.code(() -> System.out.println(
                numbers.stream()
                        .filter(integer -> integer % 2 == 0)
                        .mapToInt(LambdaStart::compute)
                        .sum()
        ));

        TimeIt.code(() -> System.out.println(
                numbers.parallelStream()
                        .filter(integer -> integer % 2 == 0)
                        .mapToInt(LambdaStart::compute)
                        .sum()
        ));
    }

    public static int compute(Integer number) {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {

        }

        return number * 2;
    }


    public void lambdaForInnerAnonymousClasses() {
        Thread th = new Thread(() -> System.out.println("in thread"));
        th.start();
        System.out.println("In main");
    }

    public void forEach() {

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        numbers.forEach(integer -> System.out.println(integer));
        numbers.forEach(System.out::println);

        numbers.stream().map(String::valueOf).forEach(System.out::println);
    }
}