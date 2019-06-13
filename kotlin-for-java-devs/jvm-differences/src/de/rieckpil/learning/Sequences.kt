package de.rieckpil.learning

fun main() {

    // Sequences -> Java Streams
    // no need to use Sequences unless we have huge collections

    val immutableMap = mapOf(
        1 to Boat("red", "Submarine", 111),
        2 to Boat("orange", "Chief", 1234),
        3 to Boat("white", "Arny", 590),
        4 to Boat("red", "Tom", 556)
    )

    println(immutableMap.asSequence().filter { it.value.color == "red" }.map { it.value.name })

    listOf("Joe", "Tom", "Jerry", "Jane").asSequence()
        .filter { println("filtering $it"); it[0] == 'J' }
        .map { println("mapping $it"); it.toUpperCase() }
        .find { it.endsWith("E") }

    println("The same without a Kotlin Sequence")

    listOf("Joe", "Tom", "Jerry", "Jane")
        .filter { println("filtering $it"); it[0] == 'J' }
        .map { println("mapping $it"); it.toUpperCase() }
        .find { it.endsWith("E") }


}

data class Boat(val color: String, val name: String, val power: Int)