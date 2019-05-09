package edu.iis.mto.time;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

public class TestableClock {

    private static ClockTimeTravel clockTimeTravel = new ClockTimeTravel(LocalDateTime.now(), OffsetDateTime.now()
                                                                                                            .getOffset());

    public static void synchronizeWithSystemClock() {
        clockTimeTravel = new ClockTimeTravel(LocalDateTime.now(), OffsetDateTime.now()
                                                                                 .getOffset());
    }

    public static void timeTravel(LocalDateTime tv) {
        clockTimeTravel.timeTravel(tv);
    }

    public static LocalDateTime getNow() {
        return LocalDateTime.ofInstant(clockTimeTravel.instant(), clockTimeTravel.getZone());
    }

}
