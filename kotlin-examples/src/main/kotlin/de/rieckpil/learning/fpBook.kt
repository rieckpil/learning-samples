package de.rieckpil.learning

fun main() {
  println(factorial(7))
  println(fib(5))
}


fun factorial(i: Int): Int {
  tailrec fun go(n: Int, acc: Int): Int =
    if (n <= 0) acc
    else go(n - 1, n * acc)
  return go(i, 1)
}

fun fib(i: Int): Int {
  tailrec fun go(n: Int): Int =
    if (n <= 1) n
    else go(n - 1) + go(n - 2)
  return go(i)
}
