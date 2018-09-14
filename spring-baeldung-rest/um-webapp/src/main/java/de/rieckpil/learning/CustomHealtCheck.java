package de.rieckpil.learning;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CustomHealtCheck implements HealthIndicator {

	@Override
	public Health health() {
		return Health.outOfService().build();
	}

}
