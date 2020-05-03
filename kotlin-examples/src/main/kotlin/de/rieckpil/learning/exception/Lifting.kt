package de.rieckpil.learning.exception

import de.rieckpil.learning.datatypes.Cons
import de.rieckpil.learning.datatypes.List
import de.rieckpil.learning.datatypes.Nil

fun main() {
  println(sequence(List.of(Some("Hello"), Some("My"), Some("Friend"))))
  println(sequence(List.of(Some("Hello"), None, Some("Friend"))))
}

// how to to with recursive call to sequence?
fun <A> sequence(xs: List<Option<A>>): Option<List<A>> =
  List.foldRight(xs, Some(Nil)) { x: Option<A>, y: Option<List<A>> -> map2(x, y) { a: A, b: List<A> -> Cons(a, b) } }

fun <A> sequenceTwo(xs: List<Option<A>>): Option<List<A>> = traverse(xs) { it }

fun <A, B> traverse(xs: List<A>, f: (A) -> Option<B>): Option<List<B>> =
  when (xs) {
    is Nil -> Some(Nil)
    is Cons ->
      map2(f(xs.head), traverse(xs.tail, f)) { b, xb ->
        Cons(b, xb)
      }
  }
