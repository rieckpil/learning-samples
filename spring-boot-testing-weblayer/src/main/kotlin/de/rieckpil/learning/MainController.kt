package de.rieckpil.learning

import org.springframework.http.CacheControl
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.WebRequest
import java.util.concurrent.TimeUnit

@RestController
class MainController(
  private val personProvider: PersonProvider
) {

  @GetMapping("/api")
  fun getMessage(webRequest: WebRequest): String {
    println(webRequest.headerNames.forEach { header ->
      println("$header:${webRequest.getHeader(header)}")
    })

    return "Hello World!"
  }

  @GetMapping("/data")
  fun getData(): ResponseEntity<List<Person>> {
    val persons = personProvider.getPersons()

    return ResponseEntity
      .ok()
      .eTag(persons.size.toString())
      .cacheControl(CacheControl.maxAge(1, TimeUnit.DAYS))
      .body(persons)
  }

}

