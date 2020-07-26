package watchgirl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class EntropyToolsTest {

    private EntropyTools underTest;

    @BeforeEach
    void setup() {
        underTest = new EntropyTools();
    }

    @Test
    void generateUuid() {
        UUID actual = underTest.generateUuid();

        Assertions.assertNotNull(actual);
    }

    @Test
    void generateSecretKey() {
        String actual = underTest.generateSecretKey();

        String expectedRegex = "[a-f0-9]{64}";
        Assertions.assertTrue(actual.matches(expectedRegex));
    }
}
