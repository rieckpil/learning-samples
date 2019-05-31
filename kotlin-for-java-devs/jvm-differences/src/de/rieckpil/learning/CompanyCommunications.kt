package de.rieckpil.learning

import java.time.Year

fun main() {

    println(CompanyCommunications.getTagLine())
    println(CompanyCommunications.getCopyrightLine())

    println(SomeClass.SomeCompanion.accessPrivateVar())
    println(SomeClass.accessPrivateVar())

    val someClass: SomeClass = SomeClass.SomeCompanion.justAssign("Hello World")

    wantsSomeInterface(object: SomeInterface {
        override fun mustImplement(num: Int): String {
            return num.toString()
        }

    })

    println(Department.ACCOUNTING.getDeptInfo())

}

enum class Department(val fullName: String, val numEmployees: Int) {
    HR("Human Resources", 12),
    IT("Information Technology", 42),
    ACCOUNTING("Accounting", 5),
    SALES("Sales", 20);

    fun getDeptInfo() = "The $fullName department contains $numEmployees employees"
 }

object CompanyCommunications {

    val currentYear = Year.now().value

    fun getTagLine() = "Our company rocks!"
    fun getCopyrightLine() = "Copyright \u00A9 $currentYear Our Company. All rights reserved"

}

class SomeClass private constructor(val someString: String){

    companion object SomeCompanion {
        private var privateVar = 6

        fun accessPrivateVar() = "I'm accessing private var $privateVar"

        fun justAssign(str: String) = SomeClass(str)
        fun upperOrLowerCase(str: String, lowerCase: Boolean): SomeClass {
            if(lowerCase) {
                return SomeClass(str.toLowerCase())
            }else {
                return SomeClass(str.toUpperCase())
            }
        }
    }
}

interface SomeInterface {
    fun mustImplement(num: Int): String
}

fun wantsSomeInterface(si: SomeInterface) {
    println("Printing from wantsSomeInterface ${si.mustImplement(42)}")
}