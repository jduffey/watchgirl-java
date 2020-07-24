package watchgirl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CameraTest {

    @Test
    public void takePhoto_logsTimeAndReceivedSignal() {
        Camera camera = new Camera();
        int time = 1;
        SignalOutput receivedSignalOutput = SignalOutput.RED;

        camera.takePhoto(time, receivedSignalOutput);

        Assertions.assertEquals(camera.getReceivedSignalOutput(time), SignalOutput.RED);
    }
}
