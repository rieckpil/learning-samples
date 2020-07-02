package de.rieckpil.learning.fpbook.propertytesting

import de.rieckpil.learning.fpbook.either.Either
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

interface Prop {
  fun check(): Either<Pair<FailedCase, SuccessCount>, SuccessCount>
  fun and(p: Prop): Prop = TODO()
}

fun choose(start: Int, stopExclusive: Int): Gen<Int> = Gen(State { rng: RNG -> nonNegativeInt(rng) }
  .map { start + (it % (stopExclusive - start)) })

fun <A> unit(a: A): Gen<A> = Gen(State.unit(a))

fun boolean(): Gen<Boolean> = Gen(State { rng -> nextBoolean(rng) })

fun <A> listOfN(n: Int, ga: Gen<A>): Gen<List<A>> = Gen(State.sequence(List(n) { ga.sample }))

fun <A> listOf(a: Gen<A>): List<Gen<A>> = TODO()

fun <A> forAll(a: Gen<A>, f: (A) -> Boolean): Prop = TODO()
