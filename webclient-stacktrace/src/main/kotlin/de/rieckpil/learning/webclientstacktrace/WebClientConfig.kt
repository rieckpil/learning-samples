package de.rieckpil.learning.webclientstacktrace

import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

@Component
class WebClientConfig(private val builder: WebClient.Builder) {

    @Bean
    fun sampleWebClient() = builder.baseUrl("https://jsonplaceholder.typicode.com/").build()

}
