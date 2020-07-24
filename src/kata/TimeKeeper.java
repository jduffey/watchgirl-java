package kata;

import java.time.Instant;

public class TimeKeeper {

    public String getCurrentUnixTime() {

        return String.valueOf(Instant.now().getEpochSecond());
    }
}
