package de.rieckpil.learning.fpbook.arrow

import arrow.core.Either
import arrow.core.extensions.fx
import de.rieckpil.learning.fpbook.exception.insuranceRateQuote



  suspend fun String.parseToInt(): Either<Throwable, Int> =
    Either.catch { this.toInt() }

  suspend fun parseInsuranceRateQuote(
    age: String,
    numberOfSpeedingTickets: String
  ): Either<Throwable, Double> {

    val maybeAge = age.parseToInt()
    val maybeNumberOfTickets = numberOfSpeedingTickets.parseToInt()

    // compiler de-sugars this to flatMap calls
    return Either.fx {
      val (a) = maybeAge
      val (t) = maybeNumberOfTickets

      // will only be called if both Either values are Right
      // no need to adapt the method signature
      // fun insuranceRateQuote(age: Int, numberOfSpeedingTickets: Int): Double
      insuranceRateQuote(a, t)
    }
  }

suspend fun main() {
  println(parseInsuranceRateQuote("18", "2"))
  println(parseInsuranceRateQuote("25", "12x"))
  println(parseInsuranceRateQuote("", "12"))
}
