package watchgirl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

public class WatchgirlIntegrationTest {

    private SignalMaker signalMaker = mock(SignalMaker.class);
    private SignalComparer signalComparer;

    @BeforeEach
    void setup() {
//        signalMaker = new SignalMaker();
        signalComparer = new SignalComparer();
    }

    /*
    SignalMaker asks for time and secret
    SignalMaker generates SignalOutput
    Camera asks for time
    Camera takes photo and captures SignalOutput
    Camera sends payload to SignalComparer
     */

//    @Test
//    void endToEndExpectedSignal() {
//        // SignalMaker device
//        String signalInput = "6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b40";
//        SignalOutput receivedSignal = signalMaker.generateSignal(signalInput);
//        SignalOutput expectedSignal = SignalOutput.RED;
//
//        boolean actual = signalComparer.compareSignal(receivedSignal, expectedSignal);
//
//        Assertions.assertTrue(actual);
//    }
//
//    @Test
//    void endToEndUnexpectedSignal() {
//        String signalInput = "6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b41";
//        SignalOutput receivedSignal = signalMaker.generateSignal(signalInput);
//        SignalOutput expectedSignal = SignalOutput.RED;
//
//        boolean actual = signalComparer.compareSignal(receivedSignal, expectedSignal);
//
//        Assertions.assertFalse(actual);
//    }

    @Test
    void e2e_generateSignal_takePhoto_sendPhotoToSignalComparer() {
    }
}
