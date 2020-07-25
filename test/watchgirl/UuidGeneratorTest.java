package watchgirl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UuidGeneratorTest {

    @Test
    void generateUuid() {
        UuidGenerator underTest = new UuidGenerator();

        String uuid = underTest.generateUuid();

        String uuidRegex = "(?i)[A-F0-9]{8}-[A-F0-9]{4}-[A-F0-9]{4}-[A-F0-9]{4}-[A-F0-9]{12}";
        Assertions.assertTrue(
                uuid.matches(uuidRegex),
                uuid);
    }
}
