package watchgirl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PhotoAnalyzerTest {

    private final HmacGenerator hmacGenerator = mock(HmacGenerator.class);
    private final Photograph photograph = mock(Photograph.class);
    private final SecretKeeper secretKeeper = mock(SecretKeeper.class);
    private PhotoAnalyzer underTest;

    @BeforeEach
    void setup() {
        underTest = new PhotoAnalyzer(hmacGenerator, secretKeeper);
    }

    @Test
    void getExpectedSignal_givenPhotograph_returnsExpectedSignal() throws Exception {
        UUID cameraId = UUID.randomUUID();
        String time = "TIME";
        String secret = "SECRET";
        SignalOutput expectedSignal = SignalOutput.RED;

        when(photograph.getCameraId()).thenReturn(cameraId);
        when(photograph.getTime()).thenReturn(time);
        when(photograph.getSignal()).thenReturn(expectedSignal);

        when(secretKeeper.getSecret(cameraId)).thenReturn(secret);

        when(hmacGenerator.generateHmac(time, secret))
                .thenReturn("0000000000000000000000000000000000000000000000000000000000000000");

        SignalOutput actual = underTest.getExpectedSignal(photograph);

        Assertions.assertEquals(expectedSignal, actual);
    }
}
