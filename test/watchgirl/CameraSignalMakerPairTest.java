package watchgirl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class CameraSignalMakerPairTest {

    @Test
    void getters() {
        UUID uuid = UUID.randomUUID();
        String secret = "SECRET";
        TimeKeeper timeKeeper = new TimeKeeper();
        HmacGenerator hmacGenerator = new HmacGenerator();
        Camera camera = new Camera(uuid, timeKeeper);
        SignalMaker signalMaker = new SignalMaker(timeKeeper, secret, hmacGenerator);

        CameraSignalMakerPair actual = new CameraSignalMakerPair(camera, signalMaker);

        Assertions.assertSame(camera, actual.getCamera());
        Assertions.assertSame(signalMaker, actual.getSignalMaker());
    }
}
