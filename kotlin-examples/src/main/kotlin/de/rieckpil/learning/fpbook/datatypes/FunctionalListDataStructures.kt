package de.rieckpil.learning.fpbook.datatypes

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

    fun sum(doubles: List<Double>): Double =
      when (doubles) {
        is Nil -> 0.0
        is Cons -> doubles.head + sum(doubles.tail)
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

    fun sumLeft(ints: List<Int>): Int = foldLeft(ints, 0, { a, b -> a + b })

    fun productLeft(ints: List<Double>): Double = foldLeft(ints, 1.0, { a, b -> a * b })

    fun <A> lengthLeft(l: List<A>): Int = foldLeft(l, 0, { acc, _ -> acc + 1 })

    fun <A> reverse(l: List<A>): List<A> = foldLeft(l, empty()) { a, b -> Cons(b, a) }

    fun <A> append(a1: List<A>, a2: List<A>): List<A> =
      when (a1) {
        is Nil -> a2
        is Cons -> Cons(a1.head, append(a1.tail, a2))
      }

    fun <A> appendRight(l1: List<A>, l2: List<A>): List<A> = foldRight(l1, l2) { a, b -> Cons(a, b) }

    // How to do this properly
    fun <A> appendLeft(l1: List<A>, l2: List<A>): List<A> = foldLeft(reverse(l1), l2) { a, b -> Cons(b, a) }

    fun <A, B> foldLeftR(xs: List<A>, z: B, f: (B, A) -> B): B =
      foldRight(xs, { b: B -> b }, { a, g -> { b -> g(f(b, a)) } })(z)

    fun <A, B> foldRightL(xs: List<A>, z: B, f: (A, B) -> B): B =
      foldLeft(xs, { b: B -> b }, { g, a -> { b -> g(f(a, b)) } })(z)

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

    fun <A> concat(l: List<List<A>>): List<A> = foldRight(l, empty()) { a, b -> append(a, b) }

    fun transform(l: List<Int>): List<Int> = foldRight(l, empty()) { a, b -> Cons(a + 1, b) }

    fun transformToString(l: List<Double>): List<String> = foldRight(l, empty()) { a, b -> Cons(a.toString(), b) }

    fun <A, B> map(xs: List<A>, f: (A) -> B): List<B> = foldRight(xs, empty()) { a, b -> Cons(f(a), b) }

    fun <A> filter(xs: List<A>, f: (A) -> Boolean): List<A> = foldRight(xs, empty()) { a, b -> if (f(a)) Cons(a, b) else b }

    fun <A> flatMap(xs: List<A>, f: (A) -> List<A>): List<A> = foldRight(xs, empty()) { a, b -> append(f(a), b) }

    fun <A> flatMapFilter(xs: List<A>, f: (A) -> Boolean): List<A> = flatMap(xs) { i -> if (f(i)) Cons(i, Nil) else Nil }

    fun addElements(listOne: List<Int>, listTwo: List<Int>): List<Int> =
      when (listOne) {
        is Nil -> Nil
        is Cons -> when (listTwo) {
          is Nil -> Nil
          is Cons -> Cons(listOne.head + listTwo.head, addElements(listOne.tail, listTwo.tail))
        }
      }

    fun <A> zipWith(listOne: List<A>, listTwo: List<A>, f: (A, A) -> A): List<A> =
      when (listOne) {
        is Nil -> Nil
        is Cons -> when (listTwo) {
          is Nil -> Nil
          is Cons -> Cons(f(listOne.head, listTwo.head), zipWith(listOne.tail, listTwo.tail, f))
        }
      }

    tailrec fun <A> startsWith(l1: List<A>, l2: List<A>): Boolean =
      when (l1) {
        is Nil -> l2 == Nil
        is Cons -> when (l2) {
          is Nil -> true
          is Cons ->
            if (l1.head == l2.head)
              startsWith(l1.tail, l2.tail)
            else false
        }
      }

    tailrec fun <A> hasSubsequence(xs: List<A>, sub: List<A>): Boolean =
      when (xs) {
        is Nil -> false
        is Cons -> when (sub) {
          is Nil -> true
          is Cons -> if (startsWith(xs, sub)) true else hasSubsequence(xs.tail, sub)
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

  // println(List.sum(List.of(1, 2, 3, 4, 5)))
  // println(List.drop(List.of(1, 2, 3, 4, 5), 2))
  // println(List.dropWhile(List.of(1, 2, 3, 4, 5)) { it < 5 })

  // println(List.init(List.of(1, 2, 3, 4, 5, 6, 7)))

  // val f = { x: Int, y: List<Int> -> Cons(x, y) }
  // println(List.foldRight(List.of(1, 2, 3), List.empty(), f))
  // println(List.length(List.of(1, 2, 3, 4, 5)))
  // println(List.sumLeft(List.of(1, 2, 3, 4)))
  // println(List.lengthLeft(List.of(1, 2, 3, 4)))

  // println(List.append(List.of(1, 2, 3), List.of(4, 5)))
  // println(List.appendRight(List.of(1, 2, 3), List.of(4, 5)))
  // println(List.appendLeft(List.of(1, 2, 3), List.of(4, 5)))
  // println(List.concat(List.of(List.of(1, 2), List.of(3, 4), List.of(5, 6))))
  // println(List.transform(List.of(1, 2, 3, 4, 5)))
  // println(List.transformToString(List.of(1.0, 2.0, 3.0, 4.0, 5.0)))
  // println(List.map(List.of(1.0, 2.0, 3.0, 4.0, 5.0)) { it * 2 })
  // println(List.map(List.of(1.0, 2.0, 3.0, 4.0, 5.0)) { "$it is stringified" })
  // println(List.filter(List.of(1, 2, 3, 4, 5)) { it % 2 == 0 })
  // println(List.flatMap(List.of(1, 2, 3)) { i -> List.of(i, i) })
  // println(List.flatMapFilter(List.of(1, 2, 3, 4, 5)) { it % 2 == 0 })

  // println(List.addElements(List.of(1, 2, 3), List.of(4, 5, 6)))
  // println(List.zipWith(List.of(1, 2, 3), List.of(4, 5, 6)) { a, b -> a + b })
  println(List.hasSubsequence(List.of(1, 2, 3), List.of(2, 3)))

}

