package sample;

import java.util.concurrent.atomic.LongAdder;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;
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

	public Sample getSample() {
		this.storedSamples.increment();
		return new Sample(message.toUpperCase(), 42);
	}

	@Gauge(unit = "count")
	public int number() {
		return this.storedSamples.intValue();
	}
}
