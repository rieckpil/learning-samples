package de.rieckpil.learning.user.control;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;

@Stateless
public class AsyncResultClient {

    private Client client;
    private WebTarget target;

    @PostConstruct
    public void init() {
        client = ClientBuilder.newBuilder()
                .readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();
        target = client.target("http://localhost:9080/resources/user/slow");
    }

    @PreDestroy
    public void destroy() {
        client.close();
    }

    public CompletionStage<Response> getResult() {
        return target.request(MediaType.APPLICATION_JSON).rx().get();
    }
}
