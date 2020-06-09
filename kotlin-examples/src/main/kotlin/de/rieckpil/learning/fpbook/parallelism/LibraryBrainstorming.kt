package de.rieckpil.learning.fpbook.parallelism

import arrow.core.extensions.list.foldable.firstOption
import arrow.core.getOrElse

class Par<A>(val get: A)

fun <A> unit(a: () -> A): Par<A> = Par(a())
fun <A> unit(a: A): Par<A> = Par(a)

fun <A> run(a: Par<A>): A = TODO()

fun <A> lazyUnit(a: () -> A): Par<A> =
  fork { unit(a()) }

fun <A> get(a: Par<A>): A = a.get

fun sum(ints: List<Int>): Int =
  if (ints.size <= 1)
    ints.firstOption().getOrElse { 0 }
  else {
    val l = ints.subList(0, ints.size / 2)
    val r = ints.subList(ints.size / 2, ints.size)
    val sumL: Par<Int> = unit { sum(l) }
    val sumR: Par<Int> = unit { sum(r) }
    sumL.get + sumR.get
  }

fun sumNext(ints: List<Int>): Par<Int> =
  if (ints.size <= 1)
    unit { ints.firstOption().getOrElse { 0 } }
  else {
    val l = ints.subList(0, ints.size / 2)
    val r = ints.subList(ints.size / 2, ints.size)
    map2(
      fork { sumNext(l) },
      fork { sumNext(r) }
    ) { lx: Int, rx: Int -> lx + rx }
  }

fun <A, B, C> map2(a: Par<A>, b: Par<B>, f: (A, B) -> C): Par<C> = TODO()

fun <A> fork(a: () -> Par<A>): Par<A> = TODO()

fun main() {
  println("Sum is ${sum(listOf(1, 2, 3, 4, 5, 6, 7))}")
}
