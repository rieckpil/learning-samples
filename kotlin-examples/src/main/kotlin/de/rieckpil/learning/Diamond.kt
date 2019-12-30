package de.rieckpil.learning

fun main() {
  println(diamond('P'))
}

fun diamond(c: Char): String {

  fun line(lineIndex: Int): String {
    val indent = order(c) - lineIndex
    val lineChar = 'A' + lineIndex
    return " ".repeat(indent) + lineChar + " ".repeat(lineIndex)
  }

  val topHalf = (0..order(c)).map(::line)
  val allLines = (topHalf + topHalf.reversed().drop(1))
    .map { l -> l + l.reversed().drop(1) }

  return allLines.joinToString("\n")
}

fun order(c: Char) = c - 'A'
