package de.rieckpil.learning

fun main() {

    val change = 4.22

    println("To show the value of change, we use \$change: $$change")

    val numerator = 10.99
    val denominator = 20.00

    println("The value of $numerator divided by $denominator is ${numerator/denominator}")


    val filePath = """C:\Users\rieckpil\Desktop"""

    println(filePath)

    val notes = """
*Hello World 123 nice raw strings
        *Test
                    *12345 ${Math.round(denominator/numerator)} + $numerator
        *Tewew
            *"2 -

    """.trimMargin("*")

    println(notes)
}