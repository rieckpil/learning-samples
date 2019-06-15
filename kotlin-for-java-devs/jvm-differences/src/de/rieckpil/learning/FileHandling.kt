package de.rieckpil.learning

import java.io.File

fun main() {

    val lines = File("test-file.txt").reader().readLines()
    lines.forEach { println(it) }

    val reader = File("test-file.txt").reader()
    val wholeFileAsString = reader.readText()
    reader.close()
    println(wholeFileAsString)

    val allLines = File("test-file.txt").reader().use {
        it.readText()
    }

    File("test-file.txt").reader().forEachLine { println(it) }
    File("test-file.txt").reader().useLines { it.forEach { println(it) } }

}