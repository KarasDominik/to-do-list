package com.karasdominik.common;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;

@Component("timeProvider")
@Profile("test")
public class FixedTimeProvider implements TimeProvider {

    public static final Instant NOW_DATE_TIME = Instant.now(Clock.fixed(
            Instant.parse("2018-08-22T10:00:00Z"), ZoneOffset.UTC));

    @Override
    public Instant now() {
        return NOW_DATE_TIME;
    }
}
