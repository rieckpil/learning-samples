package de.rieckpil.learning.fractions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FractionTest {

  @Test
  void zeroPlusZeroIsZero() {

    Fraction base = new Fraction(0);
    Fraction addend = new Fraction(0);

    Fraction result = base.add(addend);

    assertEquals(0, result.getNumerator());
  }

  @Test
  void shouldAddNegativeAndPositiveWholeNumbersWithPositiveOutcome() {

    Fraction base = new Fraction(-5);
    Fraction addend = new Fraction(10);

    Fraction result = base.add(addend);

    assertEquals(5, result.getNumerator());
    assertEquals(1, result.getDenominator());
  }

  @Test
  void shouldAddNegativeAndPositiveWholeNumbersWithNegativeOutcome() {
    Fraction base = new Fraction(-5);
    Fraction addend = new Fraction(2);

    Fraction result = base.add(addend);

    assertEquals(-3, result.getNumerator());
    assertEquals(1, result.getDenominator());
  }

  @Test
  void shouldAddTwoFractions() {
    Fraction base = new Fraction(1, 2);
    Fraction addend = new Fraction(1, 2);

    Fraction result = base.add(addend);

    assertEquals(1, result.getDenominator());
    assertEquals(1, result.getNumerator());
  }

  @Test
  void shouldAddFractionsWithDifferentDenominators() {

    Fraction base = new Fraction(3, 4);
    Fraction addend = new Fraction(1, 5);

    Fraction result = base.add(addend);

    assertEquals(19, result.getNumerator());
    assertEquals(20, result.getDenominator());
  }

  @Test
  void shouldAddFractionWithWholeNumber() {

    Fraction base = new Fraction(4, 3);
    Fraction addend = new Fraction(5);

    Fraction result = base.add(addend);

    assertEquals(19, result.getNumerator());
    assertEquals(3, result.getDenominator());
  }

  @Test
  void shouldExpressFractionInTheLowestTerms() {

    Fraction base = new Fraction(2, 6);
    Fraction addend = new Fraction(2, 6);

    Fraction result = base.add(addend);

    assertEquals(2, result.getNumerator());
    assertEquals(3, result.getDenominator());
  }

  @Test
  void shouldExpressFractionInTheLowestTermsWithMultipleReducing() {
    Fraction base = new Fraction(6, 4);
    Fraction addend = new Fraction(6, 4);

    Fraction result = base.add(addend);

    assertEquals(3, result.getNumerator());
    assertEquals(1, result.getDenominator());
  }
}
