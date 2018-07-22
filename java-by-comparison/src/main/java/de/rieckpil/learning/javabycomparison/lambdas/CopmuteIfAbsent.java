package de.rieckpil.learning.javabycomparison.lambdas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class CopmuteIfAbsent {

    private static Map<Double, Double> values = new HashMap<>();

    public static void main(String[] args) {

        values.put(1.4, 10.4);
        values.put(3.4, 115.4);
        values.put(3.2, 0.4);

        Function<Double, Double> squareFunction = factor -> factor * factor;

        Double result = values.computeIfAbsent(11.4, squareFunction);

        System.out.println(values.size());

        System.out.println(result);
    }
}
