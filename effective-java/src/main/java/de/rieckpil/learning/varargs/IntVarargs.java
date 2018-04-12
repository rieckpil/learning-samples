package de.rieckpil.learning.varargs;

public class IntVarargs {

  public static void main(String[] args) {

    IntVarargs obj = new IntVarargs();

    System.out.println("Sum 1: " + obj.buildSum(212, 1));
    System.out.println("Sum 2: " + obj.buildSum(1, 2, 2, 555, 222, 1, -3));
    System.out.println("Sum 3: " + obj.buildSum());

  }

  public int buildSum(int... numbers) {

    int sum = 0;

    for (int number : numbers) {
      sum += number;
    }

    return sum;

  }

}
