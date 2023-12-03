package com.tutorial.eventstore.util;

import java.time.Instant;

public final class TimeUtils {
    private TimeUtils() {
    }

    public static long currentUnixTime() {
        return Instant.now().toEpochMilli() / 1000;
    }

    public static long toSecondUnixTime(Instant time) {
        return time.toEpochMilli() / 1000;
    }
}
