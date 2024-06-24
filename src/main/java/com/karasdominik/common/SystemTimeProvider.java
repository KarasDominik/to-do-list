package com.karasdominik.common;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.Instant;

@Component("timeProvider")
@Profile("!test")
public class SystemTimeProvider implements TimeProvider {

    @Override
    public Instant now() {
        return Instant.now(Clock.systemUTC());
    }
}
