package de.rieckpil.learning.udemy

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

fun main() {
  runBlocking {
    launch(Dispatchers.Default) {
      println("First context: $coroutineContext")
      withContext(Dispatchers.IO) {
        println("Second context. $coroutineContext")
      }
      println("Third context $coroutineContext")
    }
  }
}
