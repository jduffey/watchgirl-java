package watchgirl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class CameraSignalMakerDevicePairTest {

    @Test
    void getters() {
        UUID uuid = UUID.randomUUID();
        String secret = "SECRET";
        TimeKeeper timeKeeper = new TimeKeeper();
        HmacGenerator hmacGenerator = new HmacGenerator();
        Camera camera = new Camera(uuid, timeKeeper);
        SignalMakerDevice signalMakerDevice = new SignalMakerDevice(timeKeeper, secret, hmacGenerator);

        CameraSignalMakerDevicePair actual = new CameraSignalMakerDevicePair(camera, signalMakerDevice);

        Assertions.assertSame(camera, actual.getCamera());
        Assertions.assertSame(signalMakerDevice, actual.getSignalMakerDevice());
    }
}
