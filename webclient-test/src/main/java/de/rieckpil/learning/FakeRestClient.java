package de.rieckpil.learning;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.net.HttpHeaders;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeFilterFunctions;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.UUID;

@Service
public class FakeRestClient {

    private WebClient webClient;
    private Logger logger = LoggerFactory.getLogger(FakeRestClient.class);
    private static final String BASE_URL = "http://localhost:8888";

    @PostConstruct
    public void init() {

        var tcpClient = TcpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 2_000)
                .doOnConnected(connection ->
                        connection.addHandlerLast(new ReadTimeoutHandler(2))
                                .addHandlerLast(new WriteTimeoutHandler(2)));

        this.webClient = WebClient.builder()
                .baseUrl(BASE_URL)
                .clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient)))
                .defaultCookie("cookieKey", "cookieValue", "teapot", "amsterdam")
                .defaultCookie("ga", UUID.randomUUID().toString())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.USER_AGENT, "I'm a teapot")
                .filter(ExchangeFilterFunctions.basicAuthentication("rieckpil", UUID.randomUUID().toString()))
                .filter(logRequest())
                .filter(logResponse())
                .build();
    }

    @HystrixCommand(fallbackMethod = "reliable")
    public String getResult() {
        return this.webClient
                .get()
                .uri("/todos")
                .retrieve()
                /*.onStatus(HttpStatus::is4xxClientError, clientResponse ->
                        Mono.error(new RuntimeException("###: " + clientResponse.statusCode().toString()))
                )
                .onStatus(HttpStatus::is5xxServerError, clientResponse ->
                        Mono.error(new RuntimeException("---: " + clientResponse.statusCode().toString()))
                )*/
                .bodyToMono(JsonNode.class)
                .block()
                .toString();
    }

    public String reliable() {
        return "{\"name\":\"Duke\"}";
    }

    private ExchangeFilterFunction logRequest() {
        return (clientRequest, next) -> {
            logger.info("Request: {} {}", clientRequest.method(), clientRequest.url());
            logger.info("--- Http Headers: ---");
            clientRequest.headers().forEach(this::logHeader);
            logger.info("--- Http Cookies: ---");
            clientRequest.cookies().forEach(this::logHeader);
            return next.exchange(clientRequest);
        };
    }

    private ExchangeFilterFunction logResponse() {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            logger.info("Response: {} {}", clientResponse.statusCode(), clientResponse.cookies());
            clientResponse.headers().asHttpHeaders()
                    .forEach((name, values) -> values.forEach(value -> logger.info("{}={}", name, value)));
            return Mono.just(clientResponse);
        });
    }

    private void logHeader(String name, List<String> values) {
        values.forEach(value -> logger.info("{}={}", name, value));
    }
}
