package de.rieckpil.learning.either

import de.rieckpil.learning.datatypes.Cons
import de.rieckpil.learning.datatypes.List
import de.rieckpil.learning.datatypes.Nil

fun <E, A> sequence(xs: List<Either<E, A>>): Either<E, List<A>> = traverse(xs) { it }

fun <E, A, B> traverse(xs: List<A>, f: (A) -> Either<E, B>): Either<E, List<B>> =
  when (xs) {
    is Nil -> Right(Nil)
    is Cons ->
      map2(f(xs.head), traverse(xs.tail, f)) { b, xb ->
        Cons(b, xb)
      }
  }

fun main() {

  val list = List.of("1", "2", "3x", "4")
  val listTwo = List.of(Right("1"), Right("2"), Left("Error"), Right("3"))

  println(sequence(listTwo))
  println(traverse(list) { catches { it.toInt() } })

}
