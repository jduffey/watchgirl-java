package watchgirl.admin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class SecretKeeperTest {

    private SecretKeeper underTest;

    @BeforeEach
    void setup() {
        underTest = SecretKeeper.getInstance();
    }

    @Test
    void secretKeeperIsSingleton() {
        SecretKeeper secretKeeper = SecretKeeper.getInstance();

        assertSame(underTest, secretKeeper);
    }

    @Test
    void registerCameraSecret() {
        UUID cameraId = UUID.randomUUID();
        String secret = "SECRET";

        underTest.registerCameraSecret(cameraId, secret);

        assertEquals(secret, underTest.getSecret(cameraId));
    }
}
