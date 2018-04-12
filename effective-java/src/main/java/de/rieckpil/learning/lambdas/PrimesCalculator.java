package de.rieckpil.learning.lambdas;

import java.math.BigInteger;
import java.util.stream.LongStream;

public class PrimesCalculator {

  public static void main(String[] args) {

    // IntStream is = IntStream.of(3, 4, 5, 6, 7);
    // is.forEach(System.out::println);

    long n = 8000000;

    System.out.println("From 2 to " + n + " there are: " + amountPrimes(n) + " primes");
  }

  public static long amountPrimes(long n) {

    return LongStream.rangeClosed(2, n).mapToObj(BigInteger::valueOf).filter(i -> i.isProbablePrime(50)).map(e -> {
      return e;
    }).count();
  }

}
