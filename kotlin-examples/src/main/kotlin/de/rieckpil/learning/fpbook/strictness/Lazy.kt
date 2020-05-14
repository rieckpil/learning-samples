package de.rieckpil.learning.fpbook.strictness

fun <A> if2(cond: Boolean, onTrue: () -> A, onFalse: () -> A): A = if (cond) onTrue() else onFalse()

val a = 10

val y = if2((a < 22),
  { println("a") },
  { println("b") }
)

fun maybeTwice1(b: Boolean, i: () -> Int) = if (b) i() + i() else 0

fun maybeTwice2(b: Boolean, i: () -> Int) {
  val j: Int by lazy(i)
  if (b) j + j else 0
}

fun main() {
  val x = maybeTwice1(true) { println("hi"); 1 + 41 }
  val y = maybeTwice2(true) { println("hi"); 1 + 41 }
}
