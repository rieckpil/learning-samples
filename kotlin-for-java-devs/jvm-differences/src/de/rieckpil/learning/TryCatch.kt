package de.rieckpil.learning

import de.rieckpil.learning.inheritance.Something
import java.lang.IllegalArgumentException
import java.lang.NumberFormatException

fun main() {

    println(getNumber("Hello World"))
    println(getNumber("1337"))
    println(getNumber("1337.1"))
    println(getNumber("1337,1"))

}

fun getNumber(str: String): Int {
    return try {
        Integer.parseInt(str)
    }catch (e: NumberFormatException) {
        0
    } finally {
        println("Final countdown")
    }
}

fun notImplementedYet(something: String): Nothing {
    throw IllegalArgumentException("Implement me")
}