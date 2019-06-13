package de.rieckpil.learning

import java.math.BigDecimal

fun main() {

    val list: MutableList<String> = mutableListOf("Hello", "Foo")

    val bdList = mutableListOf(BigDecimal(-2.4), BigDecimal(23.11))

    printCollection(list)
    printCollection(bdList)

    list.printInlineCollection()
    bdList.printInlineCollection()

    val anyList: Any = listOf("str1", "str2")

    if (anyList is List<*>) {
        println("this list contains strings")
        val stringList = anyList as List<String>
        println(stringList[0].contains("str"))
    }
}

fun <T> List<T>.printInlineCollection() {
    for (item in this) {
        println(item)
    }
}

fun <T> printCollection(collection: List<T>) {
    for (item in collection) {
        println(item)
    }
}

fun <T : Number> convertToInt(collection: List<T>) {
    for (num in collection) {
        println("${num.toInt()}")
    }
}