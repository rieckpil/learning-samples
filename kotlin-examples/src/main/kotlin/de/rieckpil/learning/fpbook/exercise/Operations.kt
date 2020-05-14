package de.rieckpil.learning.fpbook.exercise

import de.rieckpil.learning.fpbook.exception.None
import de.rieckpil.learning.fpbook.exception.Option
import de.rieckpil.learning.fpbook.exception.Some
import de.rieckpil.learning.fpbook.exception.flatMap

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

fun <A, B> Option<A>.flatMapTwo(f: (A) -> Option<B>): Option<B> = this.map(f).getOrElse { None }

fun <A> Option<A>.orElse(ob: () -> Option<A>): Option<A> =
  when (this) {
    is None -> ob()
    is Some -> this
  }

fun <A> Option<A>.orElseTwo(ob: () -> Option<A>): Option<A> = this.map { Some(it) }.getOrElse { ob() }

fun <A> Option<A>.filter(f: (A) -> Boolean): Option<A> =
  when (this) {
    is None -> None
    is Some -> if (f(this.get)) this else None
  }

fun <A> Option<A>.filterTwo(f: (A) -> Boolean): Option<A> = this.flatMap { if(f(it)) Some(it) else None }

fun main() {

  val someValue = Some("Hello World")
  val otherValue = Some(Some("Hello World"))

  // println(someValue.map { it.toUpperCase() })
  // println(None.map { "Duke" })

  // println(someValue.getOrElse { "Else" })
  // println(None.getOrElse { "Else" })

  println(otherValue.flatMap { it })
  println(otherValue.flatMapTwo { it })

  // println(None.orElse { Some("Duke") })
  // println(someValue.orElse { Some("Duke") })

  // println(someValue.filter { it.length > 15 })

}
