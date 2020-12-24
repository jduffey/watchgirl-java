package watchgirl.tools;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HmacGeneratorTest {

    private static final String MESSAGE = "MESSAGE";
    private HmacGenerator underTest;

    @BeforeEach
    void setup() {
        underTest = new HmacGenerator();
    }

    @Test
    void generateHmac_is64charsOfLowerCaseHexString() throws Exception {
        String actual = underTest.generateHmac(MESSAGE, "KEY");

        assertTrue(actual.matches("[a-f0-9]{64}"));
    }

    @Test
    void generateHmac_isDeterministic() throws Exception {
        assertEquals("92348a39deadb5d8064000f7b11dacbc00acccb1a7caa54e8767f464911ef48e",
                underTest.generateHmac(MESSAGE, "a"));

        assertEquals("7165f57708312129c478c2d70b742afbcf6efe384e1989a3d2070af246bf02ac",
                underTest.generateHmac("OTHER MESSAGE", "a"));

        assertEquals("624aecf889462ea2c2bdc7aafee4b1ecdc95b0b8205a01de942a8140b973107f",
                underTest.generateHmac(MESSAGE, "b"));
    }
}
