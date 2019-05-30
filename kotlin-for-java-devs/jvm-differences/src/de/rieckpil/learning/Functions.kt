package de.rieckpil.learning

fun main(): Unit {
    println(labelMultiply(3,4, "The result is:"))
    println(labelMultiply( label= "The result is:", operand2= 3, operand1 = 4))
    println(labelMultiply(3,4))
    println(Student("John").upperCaseFirstName())

    printStudents(Student("Duke"), Student("Josh"), Student("Hans"), label = "The heroes are:")

    val students = arrayOf(Student("Duke"), Student("Josh"), Student("Hans"))
    println("---")
    printStudents(*students)

    println(Utils().upperFirstAndLast("awesome work"))
    println("cool - day".upperFirstAndLast())

    val moreStudents = arrayOf(*students, Student("Alan"))
}

fun String.upperFirstAndLast(): String {
    val upperFirst = this.substring(0, 1).toUpperCase()
    val upperLast = this.substring(this.length - 1, this.length).toUpperCase();
    val midSection = this.substring(1, this.length - 1)
    return upperFirst + midSection + upperLast
}

inline fun labelMultiply(operand1: Int, operand2: Int, label: String = "The answer is:")
        = "$label ${operand1 * operand2}"

class Student(val firstName: String) {
    fun upperCaseFirstName() = firstName.toUpperCase()
}

fun printStudents(vararg students: Student, label: String = "The student name is") {
    for(student in students) {
        println("$label ${student.upperCaseFirstName()}")
    }
}