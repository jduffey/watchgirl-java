package watchgirl.tools;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EntropyToolsTest {

    private EntropyTools underTest;

    @BeforeEach
    void setup() {
        underTest = new EntropyTools();
    }

    @Test
    void generateUuid() {
        UUID actual = underTest.generateUuid();

        assertNotNull(actual);
    }

    @Test
    void generateSecretKey() {
        String actual = underTest.generateSecretKey();

        String expectedRegex = "[a-f0-9]{64}";
        assertTrue(actual.matches(expectedRegex));
    }
}
