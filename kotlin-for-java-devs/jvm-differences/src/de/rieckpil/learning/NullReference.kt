package de.rieckpil.learning

fun main() {

    val str: String? = "This isn't null"

    str?.let {
        println(it)
    }

    println(str?.toUpperCase())

    val nullString: String? = null
    println("What happens when we do this: ${nullString?.toUpperCase()}")

    if(nullString == str) {
        println("Do Foo")
    }

    val str2 = nullString ?: "This is the default value"
    println(str2)

    println(nullString!!.toUpperCase())
}