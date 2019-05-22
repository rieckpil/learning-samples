package de.rieckpil.learning

typealias EmplyoeeSet = Set<Employee>


fun main() {
    val time: String
    val number = 1
    var day = "Monday"

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

}