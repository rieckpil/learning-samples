package de.rieckpil.learning

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MainController(
  private val personProvider: PersonProvider
) {

  @GetMapping("/api")
  fun getMessage(): String {
    return "Hello World!"
  }

  @GetMapping("/data")
  fun getData() = personProvider.getPersons()

}

