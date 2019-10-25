package de.rieckpil.learning.proxies.security;

import de.rieckpil.learning.proxies.virtual.CustomHashMap;
import de.rieckpil.learning.proxies.virtual.CustomMap;

import java.util.concurrent.atomic.LongAdder;
import java.util.stream.IntStream;

public class SynchronizationCheck {

    public static void main(String[] args) {
        check(new CustomHashMap<>());
        check(new SynchronizedCustomMap<>(new CustomHashMap<>()));
    }

    public static void check(CustomMap<Integer, Integer> map) {
        System.out.println("Checking " + map.getClass().getSimpleName());

        try {
            IntStream.range(0, 46_000)
                    .parallel()
                    .forEach(i -> map.put(i, i * i));
        } catch (Exception e) {
            System.out.println(e);
        }

        LongAdder entries = new LongAdder();
        map.forEach((k, v) -> entries.increment());

        System.out.println("entries = " + entries.intValue());
        System.out.println("map.size() = " + map.size());
    }
}
