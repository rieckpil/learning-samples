package de.rieckpil.learning.fractions;

public class Fraction {

  private final int numerator;
  private final int denominator;

  public Fraction(int numerator, int denominator) {
    this.numerator = numerator;
    this.denominator = denominator;
  }

  public Fraction(int wholeNumber) {
    this.numerator = wholeNumber;
    this.denominator = 1;
  }

  public int getNumerator() {
    return numerator;
  }

  public int getDenominator() {
    return denominator;
  }

  public Fraction add(Fraction addend) {

    int resultNumerator = this.numerator * addend.denominator + addend.numerator * this.denominator;
    int resultDenominator = this.denominator * addend.denominator;

    System.out.println("Result numerator: " + resultNumerator);
    System.out.println("Result denominator: " + resultDenominator);

    int greatestCommonDivisor = determineGreatestCommonDivisor(resultNumerator, resultDenominator);
    System.out.println(greatestCommonDivisor);

    if (greatestCommonDivisor != 1) {
      resultNumerator = resultNumerator / greatestCommonDivisor;
      resultDenominator = resultDenominator / greatestCommonDivisor;
    }

    return new Fraction(resultNumerator, resultDenominator);
  }

  private int determineGreatestCommonDivisor(int resultNumerator, int resultDenominator) {
    if (resultDenominator == 0) {
      return resultNumerator;
    } else {
      return determineGreatestCommonDivisor(resultDenominator, resultNumerator % resultDenominator);
    }
  }
}
