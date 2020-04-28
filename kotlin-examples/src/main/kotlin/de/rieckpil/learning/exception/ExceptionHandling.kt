package de.rieckpil.learning.exception

import kotlin.math.pow

fun failingFn(i: Int): Int {
  val y: Int = throw Exception("boom")
  return try {
    val x = 42 + 5
    x + y
  } catch (e: Exception) {
    43
  }
}

data class Employee(
  val name: String,
  val department: String,
  val manager: Option<String>
)

fun lookupByName(name: String): Option<Employee> = TODO()
fun timDepartment(): Option<String> = lookupByName("Tim").map { it.department }


fun main() {
  val myList = listOf(1.0, 2.5, 6.5, 10.0)
  println(mean(myList))
  println(variance(myList))
  println(variance(listOf()))
}

fun mean(xs: List<Double>): Option<Double> =
  if (xs.isEmpty()) None
  else Some(xs.sum() / xs.size)


sealed class Option<out A> {
}

data class Some<out A>(val get: A) : Option<A>()
object None : Option<Nothing>()

fun <A, B> Option<A>.map(f: (A) -> B): Option<B> =
  when (this) {
    is None -> None
    is Some -> Some(f(this.get))
  }

fun <A> Option<A>.getOrElse(default: () -> A): A =
  when (this) {
    is None -> default()
    is Some -> this.get
  }

// -> this.map(f).getOrElse { None }
fun <A, B> Option<A>.flatMap(f: (A) -> Option<B>): Option<B> =
  when (this) {
    is None -> None
    is Some -> f(this.get)
  }

// -> this.map { Some(it) }.getOrElse { ob() }
fun <A> Option<A>.orElse(ob: () -> Option<A>): Option<A> =
  when (this) {
    is None -> ob()
    is Some -> this
  }

// -> this.flatMap { a -> if (f(a)) Some(a) else None }
fun <A> Option<A>.filter(f: (A) -> Boolean): Option<A> =
  when (this) {
    is None -> None
    is Some -> if (f(this.get)) this else None
  }

fun variance(xs: List<Double>): Option<Double> =
  mean(xs)
    .flatMap { m ->
      mean(xs.map { (it - m).pow(2) })
    }
