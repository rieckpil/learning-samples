package de.rieckpil.learning.arrow

import arrow.core.Either
import arrow.core.extensions.fx
import de.rieckpil.learning.exception.insuranceRateQuote

suspend fun main() {
  println(parseInsuranceRateQuote("18", "2"))
  println(parseInsuranceRateQuote("25", "12x"))
  println(parseInsuranceRateQuote("", "12"))
}

suspend fun String.parseToInt(): Either<Throwable, Int> =
  Either.catch { this.toInt() }

suspend fun parseInsuranceRateQuote(
  age: String,
  numberOfSpeedingTickets: String
): Either<Throwable, Double> {

  val ae = age.parseToInt()
  val te = numberOfSpeedingTickets.parseToInt()

  // compiler de-sugars this to flatMap calls
  return Either.fx {
    val (a) = ae
    val (t) = te

    // will only be called if both Either values are Right
    // no need to adapt the method signature
    // fun insuranceRateQuote(age: Int, numberOfSpeedingTickets: Int): Double
    insuranceRateQuote(a, t)
  }
}
