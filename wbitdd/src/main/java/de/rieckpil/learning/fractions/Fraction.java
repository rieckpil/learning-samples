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

    int resultNumerator = 0;
    int resultDenominator = 0;

    if (this.denominator == addend.denominator) {
      resultNumerator = this.numerator + addend.numerator;
      resultDenominator = this.denominator;
    }

    return new Fraction(resultNumerator, resultDenominator);
  }
}
