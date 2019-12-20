package de.rieckpil.learning

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
  val person = Person("duke", 13)
  println(person)

  val (name, age) = person
  println("$name is $age years old")

  listOf(person, Person("kotlin", 10)).map { (name, age) -> println("$name - $age") }

  runBlocking { countDown() }

  println("go!")
}

suspend fun countDown() = coroutineScope {
  for (count in 10 downTo 0) {
    launch {
      delay(500.toLong() * (10 - count))
      println(count)
    }
  }
}

sealed class Developer
data class BackendDeveloper(val name: String, val jdkVersion: Int) : Developer() {}
data class FrontendDeveloper(val name: String, val ecmaScriptVersion: Int) : Developer() {}
