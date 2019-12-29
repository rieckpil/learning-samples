package de.rieckpil.learning

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.net.URL

fun main() {
  GlobalScope.launch {
    measureTime {
      val users = listOf("1", "2", "3")
      val userResult = mutableListOf<Deferred<String>>()

      for (user in users) {
        userResult += async { getInformation(user) }
      }

      for (result in userResult) {
        println(result.await())
      }
    }
  }

  Thread.sleep(3000)
}

fun getInformation(id: String): String {
  return URL("https://jsonplaceholder.typicode.com/users/$id")
    .readText()
}

suspend fun getCallerIP(): String {
  return URL("https://api.ipify.org")
    .readText()
}

suspend fun measureTime(block: suspend () -> Unit) {
  val start = System.nanoTime()
  block()
  val end = System.nanoTime()
  println("${(end - start) / 1.0e9} seconds")
}

fun process(n: Int): Int {
  println("process running in ${Thread.currentThread()}")
  return n * 2
}

fun exampleSequence() {
  val sequence = sequence() {
    println("one")
    yield(1)
    println("two")
    yield(2)
    println("three")
    yield(3)
  }

  for (value in sequence) {
    println("The value is $value")
  }
}
