package watchgirl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HmacGeneratorTest {

    @Test
    void generateHmac() throws Exception {
        HmacGenerator underTest = new HmacGenerator();

        String actual = underTest.generateHmac("MESSAGE", "KEY");

        Assertions.assertTrue(actual.matches("(?i)[A-F0-9]{64}"));
    }
}
