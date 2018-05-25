package de.rieckpil.learning.springboot2book;

import de.rieckpil.learning.springboot2book.entity.Person;
import org.hibernate.validator.internal.constraintvalidators.bv.number.bound.MinValidatorForLong;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Locale;

import static org.springframework.util.DigestUtils.md5DigestAsHex;

@RestController
public class HelloWorldController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/hello")
    public String helloWorld(@RequestParam final String name) {

        // change log level remotely:
        // curl -X "POST" -H "Content-Type: application/json; charset=utf-8" -d  $'{"configuredLevel":"DEBUG"}' http://localhost:8080/actuator/loggers/de.rieckpil.learning
        log.debug("DEBUGGING the helloWorld enpoint!");
        log.info("Incoming message: " + name);
        log.info(messageSource.toString());

        return "Hello, " + name + "\n";
    }

    @GetMapping("/locale")
    public String getLocale(final Locale locale) {
        System.out.println(locale.getCountry() + " " + locale.toString());
        return locale.getDisplayName();
    }

    @GetMapping("/persons")
    public Person getPerson() {

        Person p1 = new Person(1, "Philip", 22);
        return p1;

    }

    @GetMapping("/image")
    public ResponseEntity<Resource> getImage() throws IOException {

        final Resource image;
        final String etag;

        CacheControl cacheControl = CacheControl.noCache();

        image = new ClassPathResource("/image.png");
        etag = md5DigestAsHex(image.getInputStream());

        return ResponseEntity
                    .ok()
                    .cacheControl(cacheControl)
                    .lastModified(image.lastModified())
                    .eTag(etag)
                    .contentType(MediaType.IMAGE_PNG)
                    .body(image);

    }

}
