package de.rieckpil.learning

fun main() {

    val strings = listOf("spring", "summer", "fall", "winter", "black")
    val mutableStrings = strings.toMutableList()
    println(strings.javaClass)

    val emptyList = emptyList<String>()
    println(emptyList.javaClass)

    val notNullList = listOfNotNull("hello", null, "goodbye")
    println(notNullList)

    val arrayList = arrayListOf(1, 2, 3)
    arrayList.add(4)
    arrayList.add(5)

    println(arrayList)

    val colors = arrayOf("black", "white", "green", "winter", "black")
    val colorList = colors.toList()

    println(strings.asReversed())
    println(arrayList.max())
    println(strings.getOrNull(5))
    println((colorList.zip(strings))) // returns a list of pairs
    println(strings + colorList)


    val noDuplicationList = colorList.union(strings)
    println(noDuplicationList)
    println(colors.distinct())
}