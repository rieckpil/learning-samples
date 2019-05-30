package de.rieckpil.learning

class Utils {

    fun upperFirstAndLast(str: String): String {
        val upperFirst = str.substring(0, 1).toUpperCase()
        val upperLast = str.substring(str.length - 1, str.length).toUpperCase();
        val midSection = str.substring(1, str.length - 1)
        return upperFirst + midSection + upperLast
    }

}