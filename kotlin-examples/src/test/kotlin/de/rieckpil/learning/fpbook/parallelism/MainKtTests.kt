package de.rieckpil.learning.fpbook.parallelism

import org.junit.jupiter.api.Test
import java.util.concurrent.Callable
import java.util.concurrent.Executors

internal class MainKtTests {

  @Test
  fun testMe() {
    val executorService = Executors.newFixedThreadPool(5)

    val result = executorService.submit(Callable {
      println("Inside the callable")
      return@Callable "duke"
    })

    println(result.get())
  }

}
