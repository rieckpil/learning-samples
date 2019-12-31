package de.rieckpil.learning

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.lang.Thread.sleep

suspend fun main() = coroutineScope {
  println("This is running with ${Thread.currentThread()}")

  launch {
    println("This is running with ${Thread.currentThread()}")
    sleep(500)
    println("Hello launch scope one")
  }

  launch {
    println("This is running with ${Thread.currentThread()}")
    sleep(500)
    println("Hello launch scope two")
  }

  sleep(500)
  println("Hello coroutine scope")
}
