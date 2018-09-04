import org.junit.Test;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.sse.SseEventSource;
import java.util.concurrent.locks.LockSupport;

public class SseClientTest {

    @Test
    public void testSse() {
        WebTarget target = ClientBuilder.newClient().target("http://localhost:8080/learning-java-ee/resources/car-events");

        SseEventSource eventSource = SseEventSource.target(target).build();

        eventSource.register(event -> System.out.println("event.readData() = " + event.readData()));
        eventSource.open();

        LockSupport.parkNanos(20_000_000_000L);
    }
}
