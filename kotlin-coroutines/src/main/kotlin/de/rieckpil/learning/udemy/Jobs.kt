package de.rieckpil.learning.udemy

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
  runBlocking {
    val job1 = launch {
//      delay(500L)
      println("Job1 launched")

      val job2 = launch {
        println("Job2 launched")
        delay(500L)
        println("Job2 finished")
      }

      job2.invokeOnCompletion { println("Job2 completed") }
    }

    job1.invokeOnCompletion { println("Job 1 completed") }

    delay(500L)

    println("Job1 will be cancelled")
    job1.cancel()

  }
}
