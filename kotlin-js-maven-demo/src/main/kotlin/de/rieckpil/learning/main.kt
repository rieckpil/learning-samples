package de.rieckpil.learning

import kotlinx.html.div
import kotlinx.html.dom.append
import kotlinx.html.dom.create
import kotlinx.html.span
import kotlinx.html.td
import kotlinx.html.tr
import kotlin.browser.document
import kotlin.browser.window
import kotlin.random.Random

fun main() {
  document.write("Hello, world!")

  window.onload = {
    document.body!!.append.div {
      span {
        +"Hello"
      }
    }

    val table = document.getElementById("tableBody")

    println(table)

    for (i in 10 downTo 0) {
      println("Iteration: $i")
      var tr = document.create.tr {
        td { +"${Random.nextLong()}" }
        td { +"${Random.nextLong()}" }
      }
      table?.appendChild(tr)
    }
  }
}
