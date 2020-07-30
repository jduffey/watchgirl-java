package watchgirl.tools;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimeKeeperTest {

    @Test
    public void getCurrentUnixTime_returnsTimeAsString() {
        TimeKeeper underTest = new TimeKeeper();

        assertEquals(String.class, underTest.getCurrentUnixTime().getClass());
    }
}
