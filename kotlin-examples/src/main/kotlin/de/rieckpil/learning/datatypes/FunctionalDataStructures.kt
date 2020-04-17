package de.rieckpil.learning.datatypes

sealed class List<out A> {

  companion object {

    fun <A> of(vararg aa: A): List<A> {
      val tail = aa.sliceArray(1 until aa.size)
      return if (aa.isEmpty()) Nil else Cons(aa[0], of(*tail))
    }

    fun sum(ints: List<Int>): Int =
      when (ints) {
        is Nil -> 0
        is Cons -> ints.head + sum(ints.tail)
      }

    fun product(doubles: List<Double>): Double =
      when (doubles) {
        is Nil -> 1.0
        is Cons ->
          if (doubles.head == 0.0) 0.0
          else doubles.head * product(doubles.tail)
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
}
