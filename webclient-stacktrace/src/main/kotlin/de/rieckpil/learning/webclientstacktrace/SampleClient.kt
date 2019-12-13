package de.rieckpil.learning.webclientstacktrace

import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import java.time.Duration

@Service
class SampleClient(private val sampleWebClient: WebClient) {

    fun getData() = sampleWebClient
            .get()
            .uri("/todos/{id}", 1)
            .retrieve()
            .bodyToMono(String::class.java)
            .timeout(Duration.ofNanos(1L))
            .block()
            .toString()
}
