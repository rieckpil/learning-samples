package de.rieckpil.learning.fpbook.strictness

import de.rieckpil.learning.fpbook.datatypes.List.Companion.reverse
import de.rieckpil.learning.fpbook.datatypes.Nil
import de.rieckpil.learning.fpbook.exception.None
import de.rieckpil.learning.fpbook.exception.Option
import de.rieckpil.learning.fpbook.exception.Some
import de.rieckpil.learning.fpbook.strictness.Stream.Companion.cons
import de.rieckpil.learning.fpbook.strictness.Stream.Companion.empty

sealed class Stream<out A> {
  companion object {

    fun <A> of(vararg xs: A): Stream<A> =
      if (xs.isEmpty()) empty()
      else cons({ xs[0] }, { of(*xs.sliceArray(1 until xs.size)) })

    fun <A> cons(hd: () -> A, tl: () -> Stream<A>): Stream<A> {
      val head: A by lazy { hd() }
      val tail: Stream<A> by lazy { tl() }
      return Cons({ head }, { tail })
    }

    fun <A> empty(): Stream<A> = Empty
  }
}

fun <A> Stream<A>.toList(): de.rieckpil.learning.fpbook.datatypes.List<A> =
  when (this) {
    is Empty -> Nil
    is Cons -> de.rieckpil.learning.fpbook.datatypes.Cons(this.h(), this.t().toList())
  }

fun <A> Stream<A>.toListSafe(): de.rieckpil.learning.fpbook.datatypes.List<A> {
  tailrec fun go(xs: Stream<A>, acc: de.rieckpil.learning.fpbook.datatypes.List<A>): de.rieckpil.learning.fpbook.datatypes.List<A> = when (xs) {
    is Empty -> acc
    is Cons -> go(xs.t(), de.rieckpil.learning.fpbook.datatypes.Cons(xs.h(), acc))
  }
  return reverse(go(this, Nil))
}

fun <A> Stream<A>.headOption(): Option<A> =
  when (this) {
    is Empty -> None
    is Cons -> Some(h())
  }

fun <A> Stream<A>.take(n: Int): Stream<A> =
  if (n == 0) empty()
  else when (this) {
    is Empty -> empty()
    is Cons -> cons(this.h, { this.t().take(n - 1) })
  }

fun <A> Stream<A>.drop(n: Int): Stream<A> =
  if (n == 0) this
  else when (this) {
    is Empty -> empty()
    is Cons -> this.t().drop(n - 1)
  }

fun <A> Stream<A>.takeWhile(p: (A) -> Boolean): Stream<A> =
  when (this) {
    is Empty -> empty()
    is Cons -> if (p(this.h())) cons(this.h, { this.t().takeWhile(p) }) else empty()
  }

fun <A> Stream<A>.takeWhileTwo(p: (A) -> Boolean): Stream<A> =
  foldRight({ empty() }, { h, t -> if (p(h)) cons({ h }, t) else t() })

fun <A> Stream<A>.exists(p: (A) -> Boolean): Boolean =
  when (this) {
    is Cons -> p(this.h()) || this.t().exists(p)
    else -> false
  }

fun <A> Stream<A>.existsTwo(p: (A) -> Boolean): Boolean =
  foldRight({ false }, { a, b -> p(a) || b() })

fun <A, B> Stream<A>.foldRight(z: () -> B, f: (A, () -> B) -> B): B =
  when (this) {
    is Cons -> f(this.h()) { t().foldRight(z, f) }
    else -> z()
  }

fun <A> Stream<A>.forAll(p: (A) -> Boolean): Boolean =
  foldRight({ false }, { a, b -> p(a) && b() })

data class Cons<out A>(val h: () -> A, val t: () -> Stream<A>) : Stream<A>()
object Empty : Stream<Nothing>()

fun main() {
  val x = Cons({ expensive() }, { Empty })
  val h1 = x.headOption()
  val h2 = x.headOption()

  val stream = Stream.of("dukeduke ", "mikemike", "fuchsfuchs", "foo", "bar")
  println(stream)
  println(stream.take(2).toList())
  println(stream.drop(2).toList())
  println(stream.takeWhile { it.length > 3 }.toList())
  println(stream.takeWhileTwo { it.length > 3 }.toList())

}

fun expensive(): String {
  println("Expensive operation")
  return "duke"
}
