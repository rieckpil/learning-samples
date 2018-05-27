package de.rieckpil.learning.springboot2bookreactive;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

@Component
public class DatabaseFiller implements CommandLineRunner {

    private Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Override
    public void run(String... args) throws Exception {

        Mono.just("Hello World!").subscribe(LOG::info);

        Mono.just("Hallo").flatMap(s -> Mono.just(s + ", Welt!")).subscribe(LOG::info);

        Mono.just("Hallo").concatWith(Flux.just(",", " Welt")).subscribe(LOG::info);

        Flux.interval(Duration.ofMillis(1L))
                .subscribeOn(Schedulers.parallel())
                .take(5)
                .map(l -> Long.toString(l))
                .subscribe(LOG::info);

    }
}
