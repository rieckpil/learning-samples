package de.rieckpil.learning

import com.fasterxml.jackson.databind.JsonNode
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.http.codec.ClientCodecConfigurer
import org.springframework.web.reactive.function.client.WebClient

@SpringBootApplication
class SpringBootTestingWeblayerApplication: CommandLineRunner {
  override fun run(vararg args: String?) {
    val consumer: (ClientCodecConfigurer) -> Unit = { configurer ->
      configurer.defaultCodecs().enableLoggingRequestDetails(true)
    }

    val webClient = WebClient
      .builder()
      .baseUrl("https://jsonplaceholder.typicode.com/todos")
      .codecs(consumer)
      .build()

    print(webClient.get().retrieve().bodyToMono(JsonNode::class.java).block())
  }
}

fun main(args: Array<String>) {
	runApplication<SpringBootTestingWeblayerApplication>(*args)
}
