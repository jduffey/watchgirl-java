package watchgirl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class PhotoAnalyzerTest {

    private PhotoAnalyzer photoAnalyzer;

    @BeforeEach
    void setup() {
        photoAnalyzer = new PhotoAnalyzer();
    }

    /*
    TODO: rework this. PhotoAnalyzer should:
    1. Read cameraID from photo
    2. Query SecretKeeper to get associated secret
    3. Read time from photo
    4. Generate hash from photo time and associated secret
    5. Calculate signal and compare it to signal in photo
    5a. "calculate signal" should be moved to its own class, out of SignalMaker
    5b. rename SignalMaker to SignalGenerator?
     */

    @Test
    void compareSignal_givenExpectedSignal_returnsTrue() {
        SignalOutput receivedSignal = SignalOutput.RED;
        SignalOutput expectedSignal = SignalOutput.RED;

        boolean actual = photoAnalyzer.compareSignal(receivedSignal, expectedSignal);

        Assertions.assertTrue(actual);
    }

    @ParameterizedTest
    @EnumSource(value = SignalOutput.class, names = {"GREEN", "BLUE", "WHITE"})
    void compareSignal_givenUnexpectedSignal_returnsFalse(SignalOutput receivedSignal) {
        SignalOutput expectedSignal = SignalOutput.RED;

        boolean actual = photoAnalyzer.compareSignal(receivedSignal, expectedSignal);

        Assertions.assertFalse(actual);
    }
}
