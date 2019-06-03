package de.rieckpil.learning

fun main() {

    val range = 1..5 // inclusive
    val charRange = 'a'..'z'
    val stringRange = "ABC".."XYZ"

    println(3 in range)
    println('q' in charRange)
    println("EFG" in stringRange)

    val backwardRange = 5.downTo(1)
    val reversedRange = backwardRange.reversed()

    for (c in charRange) {
        print("$c ")
    }

    for (c in "Philip") {
        println(c)
    }

    for (i in 20 downTo 15) {
        println("$i ")
    }

    for (i in 1..20 step 4) {
        print("$i ")
    }

    val seasons = arrayOf("spring", "summer", "winter", "fall")

    for (season in seasons) {
        print("$season ")
    }

    println()

    for (index in seasons.indices) {
        print("$index == ${seasons[index]} ")
    }

    seasons.forEach {
        println("Using it: $it")
    }

    seasons.forEachIndexed { index, value -> println("$index == $value") }

    for(i in 1..3) {
        println("i == $i")
        jloop@ for(j in 1..4){
            println("j == $j")
            for(k in 5..10) {
                println("k == $k")
                if(k == 8) {
                    break@jloop
                }
            }
        }
    }
}