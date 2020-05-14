package de.rieckpil.learning.fpbook.exception

import kotlin.math.abs
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

fun main() {
  val myList = listOf(1.0, 2.5, 6.5, 10.0)
  println(mean(myList))
  println(variance(myList))
  println(variance(listOf()))

  println(timDepartment().getOrElse { "No Department for Tim" }) // -> ?
  println(timManager().getOrElse { "No Manager for Tim" }) // -> ?
  println(annaManager().getOrElse { "No Manager for Anna" }) // -> ?

  println(abs(-1.0))
  // println(abs(Some(-1.0))) does not compile

  println(absLifted(Some(-1.0)))
  println(roundLifted(Some(-1.45)))


  println(toInt("duke")) // -> None
  println(toInt("")) // -> None
  println(toInt("123")) // -> Some(123)
  println(toInt("124x")) // -> None

  println(mean(emptyList())) // -> None
  println(mean(listOf(3.55, 7.77))) // -> 5.66


  val someValue = Some(-3.50)

  // println(abs(someValue)) -> does not compile

  println(absLifted(someValue)) // -> Some(3.50)
  println(absLifted(None)) // -> None


}

sealed class Option<out A>

data class Some<out A>(val get: A) : Option<A>()
object None : Option<Nothing>()


data class Employee(
  val name: String,
  val department: String,
  val manager: Option<String>
)

val employees = listOf(
  Employee("Tim", "IT", None),
  Employee("Anna", "IT", Some("Tim")),
  Employee("Duke", "Marketing", Some("Larry"))
)

fun lookupByName(name: String): Option<Employee> {
  val employee = employees.firstOrNull { it.name == name }
  return if (employee == null) None else Some(employee)
}

fun timDepartment(): Option<String> = lookupByName("Tim").map { it.department }

fun timManager(): Option<String> = lookupByName("Tim").flatMap { it.manager }

fun annaManager(): Option<String> = lookupByName("Anna").flatMap { it.manager }


fun toInt(s: String): Option<Int> =
  try {
    Some(s.toInt())
  } catch (e: NumberFormatException) {
    None
  }


fun mean(xs: List<Double>): Option<Double> =
  if (xs.isEmpty()) None
  else Some(xs.sum() / xs.size)


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

fun <A, B> Option<A>.flatMap(f: (A) -> Option<B>): Option<B> =
  when (this) {
    is None -> None
    is Some -> f(this.get)
  }

fun <A> Option<A>.orElse(ob: () -> Option<A>): Option<A> =
  when (this) {
    is None -> ob()
    is Some -> this
  }

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

fun <A, B> lift(f: (A) -> B): (Option<A>) -> Option<B> = { a -> a.map(f) }

val absLifted: (Option<Double>) -> Option<Double> = lift { kotlin.math.abs(it) }
val roundLifted: (Option<Double>) -> Option<Double> = lift { kotlin.math.round(it) }


fun parseInsuranceQuote(age: String, speedingTickets: String): Option<Double> {
  val optAge: Option<Int> = catches { age.toInt() }
  val optTickets: Option<Int> = catches { speedingTickets.toInt() }

  return map2(optAge, optTickets) { a, t ->
    insuranceRateQuote(a, t)
  }
}

fun <A, B, C> map2(
  a: Option<A>,
  b: Option<B>,
  f: (A, B) -> C
): Option<C> = a.flatMap { a -> b.map { b -> f(a, b) } }

fun <A> catches(a: () -> A): Option<A> =
  try {
    Some(a())
  } catch (e: Throwable) {
    None
  }


fun insuranceRateQuote(age: Int, numberOfSpeedingTickets: Int): Double =
  Double.MAX_VALUE / (age * numberOfSpeedingTickets)


// -> this.map(f).getOrElse { None }
// -> this.map { Some(it) }.getOrElse { ob() }
// -> this.flatMap { a -> if (f(a)) Some(a) else None }
