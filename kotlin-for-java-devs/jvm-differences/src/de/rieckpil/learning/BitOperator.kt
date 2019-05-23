package de.rieckpil.learning

fun main() {

    val x = 0x00101101
    val y = 0x11011011

    val z = x or y
    val w = x and y

    println(z)

    val something: Any = Employee("Four", 4)

    if(something is Employee) {
        println(something.name)
    }

}