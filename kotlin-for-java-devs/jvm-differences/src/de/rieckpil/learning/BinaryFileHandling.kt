package de.rieckpil.learning

import java.io.DataInputStream
import java.io.EOFException
import java.io.FileInputStream

fun main() {

    val di = DataInputStream(FileInputStream("test-file.bin"))
    var si: String

    try {
        while (true) {
            si = di.readUTF()
            println(si)
        }
    } catch (e: EOFException) {

    }
}