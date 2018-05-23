package de.rieckpil.learning.springboot2book;

import org.hibernate.validator.internal.constraintvalidators.bv.number.bound.MinValidatorForLong;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/hello")
    public String helloWorld(@RequestParam final String name) {

        // change log level remotely:
        // curl -X "POST" -H "Content-Type: application/json; charset=utf-8" -d  $'{"configuredLevel":"DEBUG"}' http://localhost:8080/actuator/loggers/de.rieckpil.learning
        log.debug("DEBUGGING the helloWorld enpoint!");
        log.info("Incoming message: " + name);

        return "Hello, " + name + "\n";
    }

    @GetMapping("/locale")
    public String getLocale(final Locale locale) {
        System.out.println(locale.getCountry() + " " + locale.toString());
        return locale.getDisplayName();
    }

}
