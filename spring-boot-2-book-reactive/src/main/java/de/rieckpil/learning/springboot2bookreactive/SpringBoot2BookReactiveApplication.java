package de.rieckpil.learning.springboot2bookreactive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@SpringBootApplication
@EnableScheduling
public class SpringBoot2BookReactiveApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot2BookReactiveApplication.class, args);

        HandlerFunction<ServerResponse> helloWorld =
                serverRequest -> ServerResponse.ok().body(
                        Mono.just("Hello, World!"), String.class
                );

        RouterFunction<ServerResponse> helloWorldRouterFunction = RouterFunctions.route(
                GET("/routerfunction"), helloWorld);
    }
}
