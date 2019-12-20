package de.rieckpil.learning

import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class SampleInitializer : CommandLineRunner {
  override fun run(vararg args: String?) {
    val person = Person("duke", 13)
    val (name, age) = person
    println("$name and $age")
  }
}
