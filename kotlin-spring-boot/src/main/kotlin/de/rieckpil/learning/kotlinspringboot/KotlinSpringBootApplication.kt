package de.rieckpil.learning.kotlinspringboot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class KotlinSpringBootApplication {
    @RestController
    class HelloWorldController {
        @GetMapping(value = ["/hello"])
        fun helloWorld(@RequestParam name: String) = "Hello, $name\n"
    }
}

fun main(args: Array<String>) {
    runApplication<KotlinSpringBootApplication>(*args)
}