package de.rieckpil.learning.udemy

import kotlinx.coroutines.*

fun main() {
  runBlocking {

    val myHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
      println("Exception handled ${throwable.localizedMessage}")
    }

    val job = GlobalScope.launch(myHandler) {
      println("Throwing exception from job")
      throw IndexOutOfBoundsException("1 -> 2")
    }

    job.join()

    val deferred = GlobalScope.async {
      println("Throwing exception from async")
      throw ArithmeticException("/ by zero")
    }

    try {
      deferred.await()
    } catch (e: RuntimeException) {
      println("Caught exception ${e.localizedMessage}")
    }

  }
}
