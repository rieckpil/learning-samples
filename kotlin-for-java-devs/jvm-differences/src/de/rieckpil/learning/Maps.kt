package de.rieckpil.learning

fun main() {

    val immutableMap = mapOf<Int, House>(
        1 to House("Green", "Main"),
        2 to House("White", "Linden")
    )

    println(immutableMap.javaClass)
    println(immutableMap)

    val mutableMap = mutableMapOf<String, House>("First" to House("Green", "Main"))
    mutableMap.putIfAbsent("Third", House("Yellow", "Burg"))

    println(mutableMap.javaClass)
    println(mutableMap)

    val pair = Pair(10, "Hello World")
    val (firstValue, secondValue) = pair
    println("firstValue: $firstValue and secondValue: $secondValue")

    for ((key, value) in mutableMap) {
        println("$key + $value")
    }

    val houseOne = House("Orange", "Church")
    val (color, street) = houseOne
    println(color)
    println(street)

}

class House(val color: String, val street: String) {
    operator fun component1() = color
    operator fun component2() = street + "LOLOL"
}