package de.rieckpil.learning.udemy

import kotlinx.coroutines.*

fun main() {
  println("Start to block")
  runBlocking {
    launch {
      delay(1000L)
      println("Task from runBlocking")
    }

    GlobalScope.launch {
      delay(500L)
      println("Taks from Global Scope")
    }

    coroutineScope {
      delay(1500L)
      println("Task from coroutineScope")
    }
  }
  println("Program execution will now continue")
}
