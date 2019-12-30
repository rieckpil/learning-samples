package de.rieckpil.learning

fun main() {
  println(diamond('C'))
}

fun diamond(c: Char): String {
  val topHalf = ('A'..c)
    .mapIndexed { lineIndex, lineChar ->
      val indent = order(c) - lineIndex
      " ".repeat(indent) + lineChar + " ".repeat(lineIndex)
    }

  val allLines = (topHalf + topHalf.reversed().drop(1))
    .map { l -> l + l.reversed().drop(1) }

  return allLines.joinToString("\n")
}

fun order(c: Char) = c - 'A'
