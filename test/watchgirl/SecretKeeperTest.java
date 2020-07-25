package watchgirl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class SecretKeeperTest {

    private SecretKeeper underTest;

    @BeforeEach
    void setup() {
        underTest = SecretKeeper.getInstance();
    }

    @Test
    void secretKeeperIsSingleton() {
        SecretKeeper secretKeeper = SecretKeeper.getInstance();

        Assertions.assertSame(underTest, secretKeeper);
    }

    @Test
    void registerCameraSecret() {
        UUID cameraId = UUID.randomUUID();
        String secret = "SECRET";

        underTest.registerCameraSecret(cameraId, secret);

        Assertions.assertEquals(secret, underTest.getSecret(cameraId));
    }
}
