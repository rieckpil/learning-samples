package de.rieckpil.learning;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@ApplicationScoped
public class ClientExecutor {

    @Inject
    @RestClient
    private SampleRestClient sampleRestClient;

    public void init(@Observes @Initialized(ApplicationScoped.class) Object init) {
        var response = sampleRestClient.fetchData();
        System.out.println(response.getStatus());
    }
}
