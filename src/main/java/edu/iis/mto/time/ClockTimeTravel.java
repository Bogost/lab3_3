package edu.iis.mto.time;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Objects;

public final class ClockTimeTravel extends Clock {

    private static void log(Object msg) {
        System.out.println(Objects.toString(msg));
    }

    public static void main(String[] args) throws InterruptedException {
        ClockTimeTravel timeTravel = new ClockTimeTravel(LocalDateTime.parse("2018-12-25T00:00:00"), ZoneOffset.of("Z"));
        log(timeTravel.instant());
        log("Sleep for 5 seconds...");
        Thread.currentThread()
              .sleep(3000);
        log(timeTravel.instant());
        Thread.currentThread()
              .sleep(3000);
        log(timeTravel.instant());
        log("Done.");
    }

    public ClockTimeTravel(LocalDateTime t0, ZoneOffset zoneOffset) {
        this.zoneOffset = zoneOffset;
        this.t0LocalDateTime = t0;
        this.t0Instant = t0.toInstant(zoneOffset);
        this.whenObjectCreatedInstant = Instant.now();
    }

    public void timeTravel(LocalDateTime tv) {
        this.t0LocalDateTime = tv;
        this.t0Instant = tv.toInstant(this.zoneOffset);
        this.whenObjectCreatedInstant = Instant.now();
    }

    @Override
    public ZoneId getZone() {
        return zoneOffset;
    }

    /** The caller needs to actually pass a ZoneOffset object here. */
    @Override
    public Clock withZone(ZoneId zone) {
        return new ClockTimeTravel(t0LocalDateTime, (ZoneOffset) zone);
    }

    @Override
    public Instant instant() {
        return nextInstant();
    }

    // PRIVATE

    /** t0 is the moment this clock object was created in Java-land. */
    private Instant t0Instant;
    private LocalDateTime t0LocalDateTime;

    private ZoneOffset zoneOffset;
    private Instant whenObjectCreatedInstant;

    /**
     * Figure out how much time has elapsed between the moment this object was created, and the moment when this method
     * is being called. Then, apply that diff to t0.
     */
    private Instant nextInstant() {
        Instant now = Instant.now();
        long diff = now.toEpochMilli() - whenObjectCreatedInstant.toEpochMilli();
        return t0Instant.plusMillis(diff);
    }

}
