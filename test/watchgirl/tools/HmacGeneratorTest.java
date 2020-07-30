package watchgirl.tools;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HmacGeneratorTest {

    @Test
    void generateHmac() throws Exception {
        HmacGenerator underTest = new HmacGenerator();

        String actual = underTest.generateHmac("MESSAGE", "KEY");

        assertTrue(actual.matches("(?i)[a-f0-9]{64}"));
    }
}
