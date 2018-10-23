package sample;

import java.util.concurrent.atomic.LongAdder;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.faulttolerance.Bulkhead;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.metrics.annotation.Gauge;

@ApplicationScoped
public class SampleCatalog {

	@Inject
	@ConfigProperty(name = "message")
	private String message;

	LongAdder storedSamples;

	@PostConstruct
	public void init() {
		this.storedSamples = new LongAdder();
	}

	@Fallback(fallbackMethod = "haSample")
	@CircuitBreaker
	@Bulkhead
	public Sample getSample() {
		this.storedSamples.increment();

		if (Math.random() < 0.5) {
			throw new RuntimeException("no sample");
		}

		return new Sample(message.toUpperCase(), 42);
	}

	public Sample haSample() {
		return new Sample("haSample", 42);
	}

	@Gauge(unit = "count")
	public int number() {
		return this.storedSamples.intValue();
	}
}
