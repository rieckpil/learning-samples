package de.rieckpil.learning

fun main() {

    val someCondition = 69 < 22

    val num = if(someCondition) 50 else 592

    println(num)

    println("The result of the if expression is ${if (someCondition){
        print("Something")
        42
    }else {
        print("Another")
        1337
    }}")

    val x = if(someCondition) {
        println("Hello World")
    }else {
        println("Hello Day")
    }

    println(x.javaClass)

}