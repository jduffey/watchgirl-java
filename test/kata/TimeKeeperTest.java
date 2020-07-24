package kata;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TimeKeeperTest {

    @Test
    public void getCurrentUnixTime_returnsTimeAsString() {
        TimeKeeper underTest = new TimeKeeper();

        Assertions.assertEquals(
                String.class,
                underTest.getCurrentUnixTime().getClass());
    }
}
