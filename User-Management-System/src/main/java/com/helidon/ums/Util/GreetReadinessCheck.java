/*
package com.helidon.ums.Util;

import java.time.Duration; // (1)
import java.util.concurrent.atomic.AtomicLong;
import jakarta.enterprise.context.ApplicationScoped;

import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;

@Readiness // (2)
@ApplicationScoped
public class GreetReadinessCheck implements HealthCheck {
    private final AtomicLong readyTime = new AtomicLong(0);


    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse.named("ReadinessCheck")  // (3)
                .status(isReady())
                .withData("time", readyTime.get())
                .build();
    }

    public void onStartUp(
            @Observes @Initialized(ApplicationScoped.class) Object init) {
        readyTime.set(System.currentTimeMillis()); // (4)
    }

    */
/**
     * Become ready after 5 seconds
     *
     * @return true if application ready
     *//*

    private boolean isReady() {
        return Duration.ofMillis(System.currentTimeMillis() - readyTime.get()).getSeconds() >= 5;
    }
}*/
