package de.rieckpil.learning

import java.math.BigDecimal

fun main() {
    val mixedListed = listOf("Hello", 12, 45.3, BigDecimal(44.44))

    val bigDecimalsOnly = getElementsOfType<BigDecimal>(mixedListed)

    println(bigDecimalsOnly)

}

inline fun <reified T> getElementsOfType(list: List<Any>): List<T> {

    var newList: MutableList<T> = mutableListOf()
    for(element in list) {
        if(element is T) {
            newList.add(element)
        }
    }

    return newList
}