package watchgirl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UuidGeneratorTest {

    @Test
    void generateUuid() {
        UuidGenerator uuidGenerator = new UuidGenerator();
        String uuid = uuidGenerator.generateUuid();
        String uuidRegex = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}";

        Assertions.assertTrue(
                uuid.matches(uuidRegex),
                uuid);
    }
}
