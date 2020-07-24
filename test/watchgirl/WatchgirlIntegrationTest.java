package watchgirl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WatchgirlIntegrationTest {

    private static final String CURRENT_TIME = "CURRENT_TIME";
    private SignalMaker signalMaker;
    private Camera camera;

    @BeforeEach
    void setup() {
        TimeKeeper timeKeeper = mock(TimeKeeper.class);
        SecretKeeper secretKeeper = new SecretKeeper("my secret");
        CustomHasher customHasher = new CustomHasher();
        signalMaker = new SignalMaker(timeKeeper, secretKeeper, customHasher);
//        PhotoAnalyzer photoAnalyzer = new PhotoAnalyzer();
        camera = new Camera(timeKeeper);

        when(timeKeeper.getCurrentUnixTime()).thenReturn(CURRENT_TIME);
    }

    /*
    SignalMaker asks for time and secret
    SignalMaker generates SignalOutput
    Camera asks for time
    Camera takes photo and captures SignalOutput
    Camera sends payload to SignalComparer
     */

    @Test
    void e2e_soFar() {
        SignalOutput generatedSignalOutput = signalMaker.generateSignal();

        camera.takePhoto(generatedSignalOutput);
        SignalOutput capturedSignalOutput = camera.getReceivedSignalOutput(CURRENT_TIME);

        Assertions.assertEquals(
                generatedSignalOutput,
                capturedSignalOutput
        );
    }
}
