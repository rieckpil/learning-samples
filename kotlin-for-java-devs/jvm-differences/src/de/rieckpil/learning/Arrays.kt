package de.rieckpil.learning

import de.rieckpil.learning.java.Dummy
import java.math.BigDecimal

fun main() {

    val names = arrayOf("Duke", "John", "Jane", "Jill", "Joe")
    val longs = arrayOf(1L, 2L, 3L)
    val longsVerbose = arrayOf<Long>(1, 2, 3, 4, 5)

    println(longsVerbose is Array<Long>)
    println(longs is Array<Long>)

    val evenNumbers = Array(16) { i -> i * 2 }

    println(evenNumbers.forEach { println(it) })

    val lotsOfNumbers = Array(100_000) { i -> i + 1 }
    val onlyZeros = Array(100) { 0 }
    val mixedArray = arrayOf("hello", 22, BigDecimal(15.4), 'a')

    Dummy.printNumbers(arrayOf(3, 4, 512).toIntArray())

    var someOtherArray = IntArray(5)
    for(number in someOtherArray) {
        println(number)
    }

    val arrayOfShort: Array<Short> = Array(5) { i -> (i + 1).toShort()}
    for(short in arrayOfShort) {
        println(short)
    }

}