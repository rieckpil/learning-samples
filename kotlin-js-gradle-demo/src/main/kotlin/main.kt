import kotlinx.html.div
import kotlinx.html.dom.append
import kotlinx.html.dom.create
import kotlinx.html.span
import kotlinx.html.td
import kotlinx.html.tr
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement
import org.w3c.dom.set
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
        td { +"rieckpil $i" }
      }
      table?.appendChild(tr)
    }

    drawCanvas()
  }

  window.localStorage["secret"] = "foo"
}

private fun drawCanvas() {
  val canvas = document.getElementById("myCanvas") as HTMLCanvasElement
  val ctx = canvas.getContext("2d") as CanvasRenderingContext2D
  with(ctx) {
    repeat(30) {
      beginPath()
      fillStyle = listOf("red", "green", "orange", "blue").random()
      rect(randomCoordinate(), randomCoordinate(), 20.0, 20.0)
      fill()
      closePath()
    }
  }
}

private fun randomCoordinate() = Random.nextDouble(0.0, 200.0)
