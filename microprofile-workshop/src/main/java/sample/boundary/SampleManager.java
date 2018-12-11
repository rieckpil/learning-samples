package sample.boundary;

import org.eclipse.microprofile.faulttolerance.Bulkhead;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Gauge;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.util.UUID;
import java.util.concurrent.atomic.LongAdder;

@ApplicationScoped
public class SampleManager {

    private LongAdder longAdder;

    @PostConstruct
    public void init() {
        this.longAdder = new LongAdder();
    }

    @Counted(monotonic = true)
    public String getNext() {
        longAdder.increment();
        return UUID.randomUUID().toString();
    }

    @Gauge(name = "count", unit = "count", tags = "best")
    public int count() {
        return longAdder.intValue();
    }

    @Fallback(SampleFallback.class)
    // @Timeout(200)
    @Bulkhead(2)
    @Retry(maxRetries = 3)
    public String getFoo() {
        System.out.println(".... called");
        throw new IllegalStateException("illegal");
    }

    public String defaultValue() {
        return "default";
    }

}
