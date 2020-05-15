package de.rieckpil.learning.udemy

import kotlinx.coroutines.*

fun main() {

  runBlocking {

//    launch(Dispatchers.Main) {
//      println("Main dispatcher. Thread ${Thread.currentThread().name}")
//    }

    launch(Dispatchers.Unconfined) {
      println("Unconfined dispatcher I. Thread ${Thread.currentThread().name}")
      delay(100)
      println("Unconfined dispatcher II. Thread ${Thread.currentThread().name}")

    }

    launch(Dispatchers.Default) {
      println("Default dispatcher. Thread ${Thread.currentThread().name}")
    }

    launch(Dispatchers.IO) {
      println("UI dispatcher. Thread ${Thread.currentThread().name}")
    }

    launch(newSingleThreadContext("MyThread")) {
      println("newSingleThreadContext dispatcher. Thread ${Thread.currentThread().name}")
    }
  }
}

