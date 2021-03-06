package de.rieckpil.learning.fpbook

fun main() {
  println(fib(10))
  println(fib(2))
  println(fib(1))
  println(fib(0))
  println(factorial(7))
  println(findFirst(arrayOf("Duke", "Foo", "Mike", "Phil", "Tom")) { it == "Phil" })
  println(findFirst(arrayOf(true, false, true, false)) { !it })
  println(isSorted(listOf(1, 10, 20, 30)) { x, y ->
    x < y
  })
  println(isSorted(listOf("Mike", "Hansi", "Dukeduke", "Alphabetz")) { x, y ->
    x.length < y.length
  })

  val curry = curry { i: Int, s: String -> s[i] }
  println(curry(1)("Hello"))

  val uncurry = uncurry { i: Int -> { s: String -> s[i] } }
  println(uncurry(1, "Hello"))

}

/**
 * Chapter II
 */

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

fun <A> findFirst(xs: Array<A>, p: (A) -> Boolean): Int {
  tailrec fun loop(n: Int): Int =
    when {
      n >= xs.size -> -1
      p(xs[n]) -> n
      else -> loop(n + 1)
    }
  return loop(0)
}

val <T> List<T>.tail: List<T>
  get() = drop(1)

val <T> List<T>.head: T
  get() = first()

fun <A> isSorted(aa: List<A>, ordered: (A, A) -> Boolean): Boolean {
  tailrec fun loop(head: A, tail: List<A>): Boolean =
    when {
      head == null -> true
      tail.isEmpty() -> true
      ordered(head, tail.head) -> loop(tail.head, tail.tail)
      else -> false
    }
  return loop(aa.head, aa.tail)
}

fun <A, B, C> partiall(a: A, f: (A, B) -> C): (B) -> C = { b -> f(a, b) }

fun <A, B, C> curry(f: (A, B) -> C): (A) -> (B) -> C = { a -> { b -> f(a, b) } }

fun <A, B, C> uncurry(f: (A) -> (B) -> C): (A, B) -> C = { a: A, b: B -> f(a)(b) }

fun <A, B, C> compose(f: (B) -> C, g: (A) -> B): (A) -> C = { f(g(it)) }
