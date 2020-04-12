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
        is Cons -> xs.tail
      }

    fun <A> setHead(xs: List<A>, x: A): List<A> =
      when (xs) {
        is Nil -> throw IllegalStateException("Not possible to set head of Nil")
        is Cons -> Cons(x, xs.tail)
      }

    fun <A> drop(l: List<A>, n: Int): List<A> {
      // page 45
      TODO()
    }

    fun <A> dropWhile(l: List<A>, f: (A) -> Boolean): List<A> {
      TODO()
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
}
