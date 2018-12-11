package sample.boundary;

import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

import javax.enterprise.context.ApplicationScoped;

@Health
@ApplicationScoped
public class HealthChecker implements HealthCheck {

    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse.named("sample").up().down().withData("test", "good").build();
    }
}
