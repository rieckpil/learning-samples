package de.rieckpil.learning

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBootKotlinDslApplication

fun main(args: Array<String>) {
  runApplication<SpringBootKotlinDslApplication>(*args)
}
