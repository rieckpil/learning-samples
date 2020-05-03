package de.rieckpil.learning.exception

import de.rieckpil.learning.datatypes.Cons
import de.rieckpil.learning.datatypes.Nil
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
  println(timDepartment().getOrElse { "No Department" })
  println(timManager().getOrElse { "No Manager" })


  println(abs(-1.0))
  // println(abs(Some(-1.0))) does not compile

  println(absO(Some(-1.0)))
  println(roundO(Some(-1.45)))
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

fun <A, B> lift(f: (A) -> B): (Option<A>) -> Option<B> = { oa -> oa.map(f) }

val absO: (Option<Double>) -> Option<Double> = lift { kotlin.math.abs(it) }
val roundO: (Option<Double>) -> Option<Double> = lift { kotlin.math.round(it) }

fun <A, B, C> map2(
  a: Option<A>,
  b: Option<B>,
  f: (A, B) -> C
): Option<C> = a.flatMap { a -> b.map { b -> f(a, b) } }

fun parseInsuranceQuote(age: String, speedingTickets: String): Option<Double> {
  val optAge: Option<Int> = catches { age.toInt() }
  val optTickets: Option<Int> = catches { speedingTickets.toInt() }

  return map2(optAge, optTickets) { a, t ->
    insuranceRateQuote(a, t)
  }
}

fun <A> catches(a: () -> A): Option<A> =
  try {
    Some(a())
  } catch (e: Throwable) {
    None
  }

fun insuranceRateQuote(age: Int, numberOfSpeedingTickets: Int): Double =
  Double.MAX_VALUE / (age * numberOfSpeedingTickets)


