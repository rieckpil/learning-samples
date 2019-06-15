package de.rieckpil.learning

fun main() {

    val shortList: List<Short> = listOf(1,2,3,4,5)
    convertToInt(shortList)

}

fun convertToInt(collection: List<Number>) {
    for(num in collection) {
        println("${num.toInt()} is the int value")
    }
}