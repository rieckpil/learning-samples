package de.rieckpil.learning

typealias EmplyoeeSet = Set<Employee>

fun main() {
    val time: String
    val number = 1
    var day = "Monday"

    val empl1 = Employee("Mary", 1)
    val empl2 = Employee("John", 2)
    val empl3 = Employee("John", 2)

    // referential equality
    println(empl1 === empl2)
    println(empl2 === empl3)

    // structural equality
    println(empl1.equals(empl2))
    println(empl2.equals(empl3))

    println("---")

    val names = arrayListOf("John", "Jane", "Mary")
    println(names[1])

    val employee1 = Employee("John Duke", 420)
    employee1.name = "Dukin Dounuts"

    val employee2: Employee
    val number2 = 100

    if (number < number2) {
        employee2 = Employee("Mike Mile", 111)
    } else {
        employee2 = Employee("John Doe", 42)
    }

    val employees: EmplyoeeSet = mutableSetOf(employee1, employee2)
    println(employees.size)

    print(employee1)
}

class Employee(var name: String, val id: Int) {

    // structural equality
    override fun equals(obj: Any?): Boolean {
        if(obj is Employee) {
            return name == obj.name && id == obj.id
        }
        return false
    }

    override fun toString(): String {
        return "Employee(name=$name, id=$id)"
    }
}