package de.rieckpil.learning.fpbook.propertytesting

import de.rieckpil.learning.fpbook.either.Either

data class Gen<A>(val sample: State<RNG, A>)

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
