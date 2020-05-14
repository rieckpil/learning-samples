package de.rieckpil.learning.udemy

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main() {
  GlobalScope.launch {
    delay(1000L)
    println("World")
  }

  print("Hello, ")
  Thread.sleep(2000L)
}
