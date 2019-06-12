package de.rieckpil.learning

fun main() {

    val set = setOf("Phil", "Tom", "Duke")
    set.plus("John")

    println(set.drop(1))

    val mutableInts = mutableSetOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    print(mutableInts.filter { it % 3 == 0 })

}