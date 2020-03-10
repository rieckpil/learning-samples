package de.rieckpil.learning

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MyController {

  @GetMapping("/endpoint")
  fun endpoint(): ResponseDto {
    return ResponseDto(data = "Some data")
  }
}

data class ResponseDto(val data: String)
