package de.rieckpil.learning.fpbook.pureness

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

fun ints(count: Int, rng: RNG): Pair<List<Int>, RNG> {
  fun go(x: Int, rng2: RNG, l: List<Int>): Pair<List<Int>, RNG> {
    if (x == 0) return Pair(l, rng2)
    val (v, rng3) = rng2.nextInt()
    return go(x - 1, rng3, l.plus(v))
  }
  return go(count, rng, emptyList())
}

typealias Rand<A> = (RNG) -> Pair<A, RNG>

val intR: Rand<Int> = { rng -> rng.nextInt() }

fun <A> unit(a: A): Rand<A> = { rng -> Pair(a, rng) }

fun main() {
  println(double(SimpleRNG(42)).first)
  println(ints(10, SimpleRNG(42)).first)
}
