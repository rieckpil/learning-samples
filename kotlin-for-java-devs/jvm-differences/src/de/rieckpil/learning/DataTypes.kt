package de.rieckpil.learning

import de.rieckpil.learning.java.Dummy

fun main() {

    var number = 13
    var myLong = 22L

    println("myLong $myLong is a Long: ${myLong is Long}")

    if (number is Int) {
        println(number)
    }

    myLong = number.toLong();

    val myByte: Byte = 111
    var myShort: Short
    myShort = myByte.toShort()

    println("$myByte -> $myShort")

    var myDouble = 42.5

    println(65.toChar())
    println(66.toChar())
    println(67.toChar())
    println(68.toChar())
    println(69.toChar())
    println(70.toChar())

    val vacationTime = false
    val onVacation = Dummy.isVacationTime(vacationTime)
    println(onVacation)
}