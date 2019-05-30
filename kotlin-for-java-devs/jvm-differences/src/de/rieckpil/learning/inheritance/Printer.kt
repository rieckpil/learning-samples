package de.rieckpil.learning.inheritance

import java.util.concurrent.ThreadLocalRandom

fun main() {
    val laserPrinter = LaserPrinter("Brother 1337")
    laserPrinter.printModel()
    println(laserPrinter.bestSellingPrice())

    val specialLaserPrinter = SpecialLaserPrinter("IBM")
    println(specialLaserPrinter.bestSellingPrice())

    val something = SomethingElse("Test")
}

abstract class Printer(val modelName: String) {
    open fun printModel() = println("The model name of the printer is $modelName")
    abstract fun bestSellingPrice(): Double
}

open class LaserPrinter(modelName: String): Printer(modelName) {
    override fun bestSellingPrice(): Double {
        return ThreadLocalRandom.current().nextDouble()
    }

    final override fun printModel() = println("LASER: model name is $modelName")
}

class SpecialLaserPrinter(modelName: String): LaserPrinter(modelName) {

    override fun bestSellingPrice(): Double {
        return super.bestSellingPrice() * -13.37
    }

}

open class Something {
    val someProperty: String

    constructor(someParameter: String) {
        someProperty = someParameter
        println("I am in the parent's constructor")
    }
}

class SomethingElse: Something {
    constructor(someOtherParameter: String): super(someOtherParameter) {
        println("I am in the child's constructor")
    }
}