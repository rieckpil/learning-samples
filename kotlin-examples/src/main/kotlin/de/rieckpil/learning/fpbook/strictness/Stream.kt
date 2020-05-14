package de.rieckpil.learning.fpbook.strictness

import de.rieckpil.learning.fpbook.exception.None
import de.rieckpil.learning.fpbook.exception.Option
import de.rieckpil.learning.fpbook.exception.Some

sealed class Stream<out A> {
  companion object {
//    smart constructors
  }
}

fun <A> Stream<A>.headOption(): Option<A> =
  when (this) {
    is Empty -> None
    is Cons -> Some(h())
  }

data class Cons<out A>(val h: () -> A, val t: () -> Stream<A>) : Stream<A>()
object Empty : Stream<Nothing>()
