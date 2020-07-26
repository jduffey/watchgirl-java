package watchgirl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class CameraSignalMakerPairTest {

    private Camera camera;
    private SignalMaker signalMaker;
    private CameraSignalMakerPair cameraSignalMakerPair;

    @BeforeEach
    void setup() {
        UUID uuid = UUID.randomUUID();
        String secret = "SECRET";
        TimeKeeper timeKeeper = new TimeKeeper();
        HmacGenerator hmacGenerator = new HmacGenerator();

        camera = new Camera(uuid, timeKeeper);
        signalMaker = new SignalMaker(timeKeeper, secret, hmacGenerator);
        cameraSignalMakerPair = new CameraSignalMakerPair(camera, signalMaker);
    }

    @Test
    void getCamera() {
        Assertions.assertSame(camera, cameraSignalMakerPair.getCamera());
    }

    @Test
    void getSignalMaker() {
        Assertions.assertSame(signalMaker, cameraSignalMakerPair.getSignalMaker());
    }
}
