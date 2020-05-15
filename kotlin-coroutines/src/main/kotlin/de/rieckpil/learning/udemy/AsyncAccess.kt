package de.rieckpil.learning.udemy

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

fun main() {
  runBlocking {
    println("Starting first call")
    val responseOne = async { getUserData(1) }

    println("Starting second call")
    val responseTwo = async { getUserData(2) }

    println("Finished calls")
  }
  println("End of main")
}


suspend fun getUserData(id: Int): Int {
  val response = HttpClient.newHttpClient().send(HttpRequest
    .newBuilder(URI("https://jsonplaceholder.typicode.com/users/1")).GET().build(),
    HttpResponse.BodyHandlers.ofString())
  println(response.body())
  return response.statusCode()
}

suspend fun doSomethingUsefulOne(): Int {
  delay(1000L)
  return 13
}

suspend fun doSomethingUsefulTwo(): Int {
  delay(1000L)
  return 29
}

suspend fun doBoth() = coroutineScope {
  val one = async { doSomethingUsefulOne() }
  val two = async { doSomethingUsefulTwo() }
  one.await() + two.await()
}
