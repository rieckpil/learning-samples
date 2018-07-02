package de.rieckpil.learning.streamsandlambdas;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Immutability {

    // javap -c target/classes/de/rieckpil/learning/streamsandlambdas/Immutability.class

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            if (i < 5) {
                System.out.println(i);
            }
        }

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        impure();

    }

    public static void impure() {

        List<Integer> numbers = Arrays.asList(1, 2, 3);

        int factor[] = new int[]{2};

        Stream<Integer> stream = numbers.stream()
                .map(e -> e * factor[0]);

        factor[0] = 0;

        stream.forEach(System.out::println);

    }
}
