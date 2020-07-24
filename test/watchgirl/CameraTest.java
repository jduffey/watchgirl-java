package watchgirl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CameraTest {

    private static final String CURRENT_TIME = "CURRENT_TIME";

    @Test
    public void takePhoto_storesTimeAndReceivedSignal() {
        TimeKeeper timeKeeper = mock(TimeKeeper.class);
        when(timeKeeper.getCurrentUnixTime()).thenReturn(CURRENT_TIME);
        Camera camera = new Camera(timeKeeper);

        camera.takePhoto(SignalOutput.RED);

        Assertions.assertEquals(
                SignalOutput.RED,
                camera.getReceivedSignalOutput(CURRENT_TIME));
    }
}
