package de.rieckpil.learning

// Book page 23

object Example {
  fun abs(n: Int): Int =
    if (n < 0) -n
    else n

  fun factorial(i: Int): Int {
    tailrec fun go(n: Int, acc: Int): Int =
      if (n <= 0) acc
      else go(n - 1, n * acc)
    return go(i, 1)
  }

  fun formatResult(name: String, n: Int, f: (Int) -> Int): String {
    val msg = "The %s of %d is %d."
    return msg.format(name, n, f(n))
  }
}

fun main() {
  println(Example.formatResult("factorial", 7, Example::factorial))
  println(Example.formatResult("absolute value", -42, Example::abs))
}
