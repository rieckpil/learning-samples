package de.rieckpil.learning

fun main() {
  println(factorial(7))
  println(fib(10))
  println(findFirst(arrayOf("Duke", "Foo", "Mike", "Phil", "Tom")) { it == "Phil" })
  println(findFirst(arrayOf(true, false, true, false)) { !it })
  println(isSorted(listOf(1, 10, 20, 30)) { x, y ->
    x < y
  })
  println(isSorted(listOf("Mike", "Hansi","Dukeduke", "Alphabetz")) { x, y ->
    x.length < y.length
  })
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
