package de.rieckpil.learning

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync

@EnableAsync
@SpringBootApplication
class SpringBootEventListenerApplication

fun main(args: Array<String>) {
  runApplication<SpringBootEventListenerApplication>(*args)
}
