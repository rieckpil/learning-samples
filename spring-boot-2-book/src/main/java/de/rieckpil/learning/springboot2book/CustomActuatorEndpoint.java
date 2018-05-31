package de.rieckpil.learning.springboot2book;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.boot.actuate.endpoint.web.WebEndpointResponse;
import org.springframework.http.HttpStatus;

import javax.xml.ws.WebEndpoint;
import java.util.concurrent.atomic.AtomicInteger;

@Endpoint(id = "custom")
public class CustomActuatorEndpoint {

    private final AtomicInteger cnt = new AtomicInteger();

    @ReadOperation
    public String someReadOperation() {
        return "Current value " + cnt.get();
    }

    @WriteOperation
    public String someWriteOperation() {
        return "New value " + cnt.incrementAndGet();
    }

    @ReadOperation
    public WebEndpointResponse<Void> otherReadOperation(@Selector String name) {
        return new WebEndpointResponse<>(HttpStatus.NOT_IMPLEMENTED.value());
    }

}
