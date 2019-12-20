package de.rieckpil.learning

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.function.RouterFunction
import org.springframework.web.servlet.function.ServerRequest
import org.springframework.web.servlet.function.ServerResponse
import org.springframework.web.servlet.function.router

@Configuration
class RoutesConfig {

  @Bean
  fun routes(): RouterFunction<ServerResponse> = router {
    for (route in listOf("hello", "route", "kotlin")) {
      GET("/${route}") {
        dynamicRoute(it, route)
      }
    }
  }

  fun dynamicRoute(serverRequest: ServerRequest, message: String) = ServerResponse.ok().body("Hello World from DSL: $message")

}
