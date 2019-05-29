package de.rieckpil.learning

val MY_CONSTANT= 100

fun main() {

    val worker = Worker("John", false)
    println(worker.firstName)
    println(worker.fullTime)

    println(MY_CONSTANT)

}

data class Car(val color: String, val model: String, val year: Int) {

}

// Access modifiers in Kotlin: private, protected, public, internal
class Worker(val firstName: String, fullTime: Boolean = true) {

    var fullTime = fullTime
    get() {
        println("Running the custom get")
        return field;
    }
    set(value) {
        println("Running the custom set")
        field = value
    }

    /*var fullTime: Boolean = true

    constructor(firstName: String, fullTime: Boolean): this(firstName) {
        this.fullTime = fullTime
    }*/

}