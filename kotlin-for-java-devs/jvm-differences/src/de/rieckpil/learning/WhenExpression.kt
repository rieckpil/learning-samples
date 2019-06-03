package de.rieckpil.learning

import java.math.BigDecimal

enum class Season {
    SPRING, SUMMER, FALL, WINTER
}

fun main() {

    val num = 200

    when(num) {
        100 -> println("100")
        200, 222, 242, in 255..299 -> println("in range")
        300 -> println("300")
        else -> println("Nothing applies")
    }

    val something: Any = "Hello World"

    when(something) {
        is String -> println(something.toUpperCase())
        is BigDecimal -> println(something.remainder(BigDecimal(10.5)))
        is Int -> println(something)
    }

    val timeOfYear = Season.SPRING

    val str = when(timeOfYear) {
        Season.SPRING -> "Getting hot"
        Season.SUMMER -> "Hooot"
        Season.FALL -> "Getting cold"
        Season.WINTER -> "Cooold"
    }

    println(str)

}