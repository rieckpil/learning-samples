package de.rieckpil.learning;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@RestController
public class FakeRestController {

    @Autowired
    private FakeRestClient fakeRestClient;

    private Logger logger = LoggerFactory.getLogger(FakeRestController.class);

    @ExceptionHandler(WebClientResponseException.class)
    public void logWebClientResponseException(WebClientResponseException ex) {
        logger.warn("Error while access FakeRestClient", ex);
    }

    @GetMapping
    public String getResponse() {
        return fakeRestClient.getResult();
    }

}
