package de.rieckpil.learning.datatypes

sealed class List<out A> {

  companion object {

    fun <A> of(vararg aa: A): List<A> {
      val tail = aa.sliceArray(1 until aa.size)
      return if (aa.isEmpty()) Nil else Cons(aa[0], of(*tail))
    }

    fun <A> empty(): List<A> = Nil

    fun sum(ints: List<Int>): Int =
      when (ints) {
        is Nil -> 0
        is Cons -> ints.head + sum(ints.tail)
      }

    fun product(doubles: List<Double>): Double =
      when (doubles) {
        is Nil -> 1.0
        is Cons -> doubles.head * product(doubles.tail)
      }

    fun <A> tail(xs: List<A>): List<A> =
      when (xs) {
        is Nil -> throw IllegalStateException("Not possible to get tail of Nil")
        is Cons -> drop(xs, 1)
      }

    fun <A> setHead(xs: List<A>, x: A): List<A> =
      when (xs) {
        is Nil -> throw IllegalStateException("Not possible to set head of Nil")
        is Cons -> Cons(x, xs.tail)
      }

    fun <A> drop(l: List<A>, n: Int): List<A> {
      return if (n == 0) l
      else when (l) {
        is Nil -> throw IllegalStateException("Not possible to drop Nil")
        is Cons -> drop(l.tail, n - 1)
      }
    }

    fun <A> dropWhile(l: List<A>, f: (A) -> Boolean): List<A> {
      return when (l) {
        is Nil -> l
        is Cons -> if (f(l.head)) dropWhile(l.tail, f) else l
      }
    }

    fun <A> init(l: List<A>): List<A> =
      when (l) {
        is Nil -> throw java.lang.IllegalStateException("Not possible here")
        is Cons -> when (l.tail) {
          is Nil -> Nil
          is Cons -> Cons(l.head, init(l.tail))
        }
      }

    fun sumRight(ints: List<Int>): Int = foldRight(ints, 0, { a, b -> a + b })

    fun productRight(dbs: List<Double>): Double = foldRight(dbs, 1.0, { a, b -> a * b })

    fun <A> length(l: List<A>): Int = foldRight(l, 0) { _, acc -> acc + 1 }

    fun <A, B> foldRight(l: List<A>, z: B, f: (A, B) -> B): B =
      when (l) {
        is Nil -> z
        is Cons -> f(l.head, foldRight(l.tail, z, f))
      }

    tailrec fun <A, B> foldLeft(l: List<A>, z: B, f: (B, A) -> B): B =
      when (l) {
        is Nil -> z
        is Cons -> foldLeft(l.tail, f(z, l.head), f)
      }

    fun sumLeft(ints: List<Int>): Int = foldLeft(ints, 0, { a, b -> a + b })

    fun productLeft(ints: List<Double>): Double = foldLeft(ints, 1.0, { a, b -> a * b })

    fun <A> lengthLeft(l: List<A>): Int = foldLeft(l, 0, { acc, _ -> acc + 1 })
  }
}

object Nil : List<Nothing>()

data class Cons<out A>(val head: A, val tail: List<A>) : List<A>()

val exampleOne: List<Double> = Nil
val exampleTwo: List<Int> = Cons(1, Nil)
val exampleThree: List<String> = Cons("a", Cons("b", Nil))

fun main() {
  val ints = List.of(1, 2, 3, 4, 5)
  println(List.sum(ints))

  val droppedResult = List.drop(ints, 2)
  val droppedWhile = List.dropWhile(ints) {
    it < 5
  }

  println(droppedWhile)

  // println(List.init(List.of(1, 2, 3, 4, 5, 6, 7)))

  // val f = { x: Int, y: List<Int> -> Cons(x, y) }
  // println(List.foldRight(List.of(1, 2, 3), List.empty(), f))
  // println(List.length(List.of(1, 2, 3, 4, 5)))


  println(List.sumLeft(List.of(1, 2, 3, 4)))
  println(List.lengthLeft(List.of(1, 2, 3, 4)))

}
