package de.rieckpil.microprofile.health.boundary;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

@Health
@ApplicationScoped
public class LivenessCheck implements HealthCheck {

	// @Override shows a problem with eclipse
	public HealthCheckResponse call() {
       return HealthCheckResponse.named("ping").
        up().
        withData("duke", "lives not").
        build();
	}
	
}
