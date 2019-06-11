package de.rieckpil.learning

import java.lang.StringBuilder

fun main() {

    run {
        println("I'm in a lambda")
    }

    val powerCars = listOf(
        PowerCar("BMW", "X4", 400),
        PowerCar("AUDI", "S3", 300),
        PowerCar("Mercedes", "S300", 400),
        PowerCar("SEAT", "LEON", 122),
        PowerCar("Porsche", "Cayenne", 333)
    );

    println(powerCars.minBy(PowerCar::power))
    println(powerCars.maxBy { it.power })
    println(countTo100Lambda())
    run(::topLevel)

    findByLastModelName(powerCars, "BMW")
    findByLastModelName(powerCars, "VW")

    "Some String".apply outerApply@{
        "Another String".apply {
            println(toLowerCase())
            println(this@outerApply.toUpperCase())
        }
    }
}

fun findByLastModelName(powerCars: List<PowerCar>, modelName: String) {
    powerCars.forEach returnBlock@ {
        if(it.model == modelName) {
            println("There is a PowerCar with modelName: $modelName")
            return@returnBlock
        }
    }
    println("There no PowerCar with modelName: $modelName")
}

fun countTo100(): String {
    val numbers = StringBuilder()
    for (i in 1..99) {
        numbers.append(i)
        numbers.append(", ")
    }
    numbers.append(100)
    return numbers.toString()
}

fun countTo100Lambda() = with(StringBuilder()) {
    for (i in 1..99) {
        this.append(i)
        append(", ")
    }
    append(100)
    toString()
}

fun useParameter(powerCars: List<PowerCar>, num: Int) {
    powerCars.forEach {
        println(it.model)
        println(num)
    }
}

fun topLevel() = println("I'm in a function")

data class PowerCar(val model: String, val engine: String, val power: Int)