package de.rieckpil.learning.fpbook.either

sealed class Either<out E, out A>

data class Left<out E>(val value: E) : Either<E, Nothing>()
data class Right<out A>(val value: A) : Either<Nothing, A>()

fun mean(xs: List<Double>): Either<String, Double> =
  if (xs.isEmpty())
    Left("mean of empty list!")
  else Right(xs.sum() / xs.size)

fun main() {
  println(mean(listOf(4.33, 5.3)))
  println(mean(listOf()))
  println(safeDiv(2, 4))
  println(safeDiv(2, 0))
}

fun safeDiv(x: Int, y: Int): Either<Exception, Int> = catches { x / y }

fun <A> catches(a: () -> A): Either<Exception, A> =
  try {
    Right(a())
  } catch (e: Exception) {
    Left(e)
  }

fun <E, A, B> Either<E, A>.map(f: (A) -> B): Either<E, B> =
  when (this) {
    is Left -> this
    is Right -> Right(f(this.value))
  }

fun <E, A, B> Either<E, A>.flatMap(f: (A) -> Either<E, B>): Either<E, B> =
  when (this) {
    is Left -> this
    is Right -> f(this.value)
  }

fun <E, A> Either<E, A>.orElse(f: () -> Either<E, A>): Either<E, A> =
  when (this) {
    is Left -> f()
    is Right -> this
  }

fun <E, A, B, C> map2(a: Either<E, A>, b: Either<E, B>, f: (A, B) -> C): Either<E, C> =
  a.flatMap { a -> b.map { b -> f(a, b) } }
