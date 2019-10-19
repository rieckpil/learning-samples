package de.rieckpil.learning

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    repeat(100_000) { i ->
        launch {
            delay(1000L)
            print("$i,")
        }
    }
}
