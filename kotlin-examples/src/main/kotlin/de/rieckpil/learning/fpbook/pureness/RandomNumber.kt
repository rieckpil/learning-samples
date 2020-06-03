package de.rieckpil.learning.fpbook.pureness

import de.rieckpil.learning.fpbook.datatypes.Cons
import de.rieckpil.learning.fpbook.datatypes.Nil
import de.rieckpil.learning.fpbook.datatypes.List
import de.rieckpil.learning.fpbook.datatypes.List.Companion.foldRight

import kotlin.collections.List as KotlinList

interface RNG {
  fun nextInt(): Pair<Int, RNG>
}

data class SimpleRNG(val seed: Long) : RNG {
  override fun nextInt(): Pair<Int, RNG> {
    val newSeed = (seed * 0x5DEECE66DL + 0xBL) and 0xFFFFFFFFFFFFL
    val nextRNG = SimpleRNG(newSeed)
    val n = (newSeed ushr 16).toInt()
    return Pair(n, nextRNG)
  }
}

fun nonNegativeInt(rng: RNG): Pair<Int, RNG> {
  val (i1, rng2) = rng.nextInt()
  return Pair(if (i1 < 0) -(i1 + 1) else i1, rng2)
}

fun double(rng: RNG): Pair<Double, RNG> {
  val (nonNegative, rng2) = nonNegativeInt(rng)
  return Pair(nonNegative / (Int.MAX_VALUE.toDouble() + 1), rng2)
}

fun intDouble(rng: RNG): Pair<Pair<Int, Double>, RNG> {
  val (i, rng2) = rng.nextInt()
  val (d, rng3) = double(rng2)
  return Pair(Pair(i, d), rng3)
}

fun doubleInt(rng: RNG): Pair<Pair<Double, Int>, RNG> {
  val (id, rng2) = intDouble(rng)
  val (i, d) = id
  return Pair(Pair(d, i), rng2)
}

fun double3(rng: RNG): Pair<Triple<Double, Double, Double>, RNG> {
  val (d1, rng2) = double(rng)
  val (d2, rng3) = double(rng2)
  val (d3, rng4) = double(rng3)
  return Pair(Triple(d1, d2, d3), rng4)
}

fun ints(count: Int, rng: RNG): Pair<KotlinList<Int>, RNG> {
  tailrec fun go(x: Int, rng2: RNG, l: KotlinList<Int>): Pair<KotlinList<Int>, RNG> {
    if (x == 0) return Pair(l, rng2)
    val (v, rng3) = rng2.nextInt()
    return go(x - 1, rng3, l.plus(v))
  }
  return go(count, rng, emptyList())
}

typealias Rand<A> = (RNG) -> Pair<A, RNG>

val intR: Rand<Int> = { rng -> rng.nextInt() }

fun <A> unit(a: A): Rand<A> = { rng -> Pair(a, rng) }

fun nonNegativeEven(): Rand<Int> =
  map(::nonNegativeInt) { it - 1 % 2 }

val doubleR: Rand<Double> =
  map(::nonNegativeInt) {
    it / (Int.MAX_VALUE.toDouble() + 1)
  }

fun <A, B> map(s: Rand<A>, f: (A) -> B): Rand<B> =
  { rng ->
    val (a, rng2) = s(rng)
    Pair(f(a), rng2)
  }

fun <A, B, C> map2(
  ra: Rand<A>,
  rb: Rand<B>,
  f: (A, B) -> C
): Rand<C> = { r1: RNG ->
  val (raValue, r2) = ra(r1)
  val (rbValue, r3) = rb(r2)
  Pair(f(raValue, rbValue), r3)
}

fun <A, B> both(ra: Rand<A>, rb: Rand<B>): Rand<Pair<A, B>> =
  map2(ra, rb) { a, b -> Pair(a, b) }

val intDoubleR: Rand<Pair<Int, Double>> = both(intR, doubleR)
val doubleIntR: Rand<Pair<Double, Int>> = both(doubleR, intR)

//fun <A> sequence(fs: List<Rand<A>>): Rand<List<A>> = { r1: RNG ->
//  var rnd = r1
//  val list = mutableListOf<A>()
//  for (value in fs) {
//    val (value, nextRn) = value(rnd)
//    rnd = nextRn
//    list.add(value)
//  }
//  Pair(list, rnd)
//}

fun <A> sequence(fs: List<Rand<A>>): Rand<List<A>> = { rng ->
  when (fs) {
    is Nil -> unit(List.empty<A>())(rng)
    is Cons -> {
      val (a, nrng) = fs.head(rng)
      val (xa, frng) = sequence(fs.tail)(nrng)
      Pair(Cons(a, xa), frng)
    }
  }
}

fun nonNegativeLessThan_A(n: Int): Rand<Int> =
  map(::nonNegativeInt) { it % n }

// n: 5 i: 12 mod: 2
fun nonNegativeIntLessThan(n: Int): Rand<Int> =
  flatMap(::nonNegativeInt) { i ->
    val mod = i % n
    if (i + (n - 1) - mod >= 0) unit(mod)
    else nonNegativeIntLessThan(n)
  }

fun <A, B> flatMap(f: Rand<A>, g: (A) -> Rand<B>): Rand<B> = { r1: RNG ->
  val (value, nextRng) = f(r1)
  g(value)(nextRng)
}

fun <A, B> map_flatMap(s: Rand<A>, f: (A) -> B): Rand<B> = flatMap(s) { a -> unit(f(a)) }

fun <A, B, C> map2_flatMap(
  ra: Rand<A>,
  rb: Rand<B>,
  f: (A, B) -> C
): Rand<C> = flatMap(ra) { a ->
  map(rb) { b ->
    f(a, b)
  }
}

fun rollDie(): Rand<Int> = map(nonNegativeIntLessThan(6)) { it + 1 }

fun <S, A, B> genericMap(
  sa: (S) -> Pair<A, S>,
  f: (A) -> (B)
): (S) -> Pair<B, S> = { intialState: S ->
  val (a, nextState) = sa(intialState)
  Pair(f(a), nextState)
}

//typealias State<S, A> = (S) -> Pair<A, S>

data class State<S, out A>(val run: (S) -> Pair<A, S>) {
  companion object {
    fun <S, A> unit(a: A): State<S, A> =
      State { s: S -> Pair(a, s) }

    fun <S, A, B, C> map2(
      ra: State<S, A>,
      rb: State<S, B>,
      f: (A, B) -> C
    ): State<S, C> =
      ra.flatMap { a ->
        rb.map { b ->
          f(a, b)
        }
      }

    fun <S, A> sequence(fs: List<State<S, A>>): State<S, List<A>> =
      foldRight(fs, unit(List.empty<A>()),
        { f, acc ->
          map2(f, acc) { h, t -> Cons(h, t) }
        }
      )
  }

  fun <B> map(f: (A) -> B): State<S, B> =
    flatMap { a -> unit<S, B>(f(a)) }

  fun <B> flatMap(f: (A) -> State<S, B>): State<S, B> =
    State { s: S ->
      val (a: A, s2: S) = this.run(s)
      f(a).run(s2)
    }
}

fun main() {
//  println(double(SimpleRNG(42)).first)
//  println(ints(10, SimpleRNG(42)).first)
  println(nonNegativeEven()(SimpleRNG(13)))
  println(doubleR(SimpleRNG(13)))
}
