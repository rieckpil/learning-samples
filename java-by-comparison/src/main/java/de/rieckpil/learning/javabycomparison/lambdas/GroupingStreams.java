package de.rieckpil.learning.javabycomparison.lambdas;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupingStreams {

    public static void main(String[] args) {

        List<String> names = new ArrayList<>();

        names.add("Philip");
        names.add("Tom");
        names.add("Hans");
        names.add("Hans");
        names.add("Mike");
        names.add("Philip");
        names.add("Phil");
        names.add("Tom");
        names.add("Andre");

        Map<String, Long> result = names.stream()
                .filter(name -> name.length() > 3)
                .collect(Collectors.groupingBy(String::toString, Collectors.counting()));


        result.forEach((k, v) -> System.out.println(k + " " + v));

    }
}
