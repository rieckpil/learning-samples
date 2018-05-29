package de.rieckpil.learning.springboot2book;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;


@RunWith(SpringRunner.class)
@RestClientTest(ExternalSystemCallService.class)
public class ExternalSystemCallServiceTest {

    @Autowired
    private ExternalSystemCallService externalSystemCallService;

    @Autowired
    private MockRestServiceServer server;

    @Test
    public void getRemoteInfoShouldWork() {

        this.server
                .expect(requestTo("https://biking.michael-simons.eu/api/system/info"))
                .andRespond(withSuccess("{\"spring-boot.version\": \"4711\"}", MediaType.APPLICATION_JSON));

        assertThat(externalSystemCallService.getRemoteInfo(), is("4711"));

    }

}