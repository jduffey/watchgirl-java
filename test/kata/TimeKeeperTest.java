package kata;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TimeKeeperTest {

    @Test
    public void getCurrentUnixTime() {
        TimeKeeper underTest = new TimeKeeper();

        String actual = underTest.getCurrentUnixTime();

        Assertions.assertEquals(actual, "WHAT TO DO WITH THIS TEST?");
    }
}
