package de.rieckpil.learning.fpbook.propertytesting

import arrow.core.getOrElse
import arrow.core.toOption
import kotlin.math.absoluteValue

data class Gen<A>(val sample: State<RNG, A>) {
  companion object {
    fun <A> listOfN(gn: Gen<Int>, ga: Gen<A>): Gen<List<A>> =
      gn.flatMap { n -> listOfN(n, ga) }

    fun <A> union(ga: Gen<A>, gb: Gen<A>): Gen<A> =
      boolean().flatMap { if (it) ga else gb }

    fun <A> weighted(
      pga: Pair<Gen<A>, Double>,
      pgb: Pair<Gen<A>, Double>
    ): Gen<A> {
      val weightA = pga.second
      val weightB = pgb.second

      val genA = pga.first
      val genB = pgb.first

      val prob = weightA.absoluteValue / (weightA.absoluteValue + weightB.absoluteValue)
      return Gen(State { rng: RNG -> double(rng) })
        .flatMap { d ->
          if (d < prob) genA else genB
        }
    }
  }

  fun <B> flatMap(f: (A) -> Gen<B>): Gen<B> =
    Gen(this.sample.flatMap { a -> f(a).sample })
}

typealias SuccessCount = Int
typealias FailedCase = String
typealias TestCases = Int

// typealias Result = Either<Pair<FailedCase, SuccessCount>, SuccessCount>
// typealias Result = Option<Pair<FailedCase, SuccessCount>>

sealed class Result {
  abstract fun isFalsified(): Boolean
}

object Passed : Result() {
  override fun isFalsified(): Boolean = false
}

data class Falsified(
  val failure: FailedCase,
  val successes: SuccessCount
) : Result() {
  override fun isFalsified(): Boolean = true
}

data class Prop(val check: (TestCases, RNG) -> Result) {
  fun and(p: Prop): Prop = Prop { n: TestCases, rng: RNG ->
      val resultOne = this.check(n, rng)
      val resultTwo = p.check(n, rng)

    when {
      resultOne.isFalsified() -> {
        resultOne
      }
      resultTwo.isFalsified() -> {
        resultTwo
      }
      else -> {
        Passed
      }
    }

  }

  fun or(p: Prop): Prop = TODO()
}

fun choose(start: Int, stopExclusive: Int): Gen<Int> = Gen(State { rng: RNG -> nonNegativeInt(rng) }
  .map { it % (stopExclusive - start) + start })

fun <A> unit(a: A): Gen<A> = Gen(State.unit(a))

fun boolean(): Gen<Boolean> = Gen(State { rng -> nextBoolean(rng) })

fun <A> listOfN(n: Int, ga: Gen<A>): Gen<List<A>> = Gen(State.sequence(List(n) { ga.sample }))

fun <A> listOf(a: Gen<A>): List<Gen<A>> = TODO()

fun <A> forAll(ga: Gen<A>, f: (A) -> Boolean): Prop =
  Prop { n: TestCases, rng: RNG ->
    randomSequence(ga, rng).mapIndexed { i, a ->
      try {
        if (f(a)) Passed
        else Falsified(a.toString(), i)
      } catch (e: Exception) {
        Falsified(buildMessage(a, e), i)
      }
    }.take(n)
      .find { it.isFalsified() }
      .toOption()
      .getOrElse { Passed }
  }

private fun <A> randomSequence(ga: Gen<A>, rng: RNG): Sequence<A> =
  sequence {
    val (a: A, rng2: RNG) = ga.sample.run(rng)
    yield(a)
    yieldAll(randomSequence(ga, rng2))
  }

private fun <A> buildMessage(a: A, e: Exception) =
  """
  |test case: $a
  |generated and exception: ${e.message}
  |stacktrace:
  |${e.stackTrace.joinToString("\n")}
""".trimMargin()
