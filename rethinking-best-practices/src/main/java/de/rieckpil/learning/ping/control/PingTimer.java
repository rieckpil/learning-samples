package de.rieckpil.learning.ping.control;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.time.Instant;

@Singleton
@Startup
public class PingTimer {

    @Schedule(second = "*/10", minute = "*", hour = "*", persistent = false)
    public void ping() {
        System.out.println(Instant.now());
    }
}
