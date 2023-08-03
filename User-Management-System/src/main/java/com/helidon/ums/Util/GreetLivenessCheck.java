package com.helidon.ums.Util;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

@Liveness // (1)
@ApplicationScoped // (2)
public class GreetLivenessCheck implements HealthCheck {
   // private GreetingProvider provider;

    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse.named("LivelinessCheck")  // (3)
                .up()
                .withData("time", System.currentTimeMillis())
                .build();
    }
}