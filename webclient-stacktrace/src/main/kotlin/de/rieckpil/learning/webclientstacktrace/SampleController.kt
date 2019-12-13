package de.rieckpil.learning.webclientstacktrace

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class SampleController(private val sampleClient: SampleClient) {

    @GetMapping
    fun getSampleData() = sampleClient.getData()

}