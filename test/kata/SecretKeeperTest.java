package kata;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SecretKeeperTest {

    @Test
    void getAndSetSecret() {
        String secret = "SECRET";
        SecretKeeper underTest = new SecretKeeper();

        Assertions.assertEquals(
                secret,
                underTest.getSecret()
        );
    }
}
