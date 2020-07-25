package watchgirl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EntropyToolsTest {

    private EntropyTools underTest;

    @BeforeEach
    void setup() {
        underTest = new EntropyTools();
    }

    @Test
    void generateUuid() {
        String actual = underTest.generateUuid();

        String expectedRegex ="[a-f0-9]{8}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{12}";
        Assertions.assertTrue(actual.matches(expectedRegex));
    }

    @Test
    void generateSecretKey() {
        String actual = underTest.generateSecretKey();

        String expectedRegex = "[a-f0-9]{64}";
        Assertions.assertTrue(actual.matches(expectedRegex));
    }
}
