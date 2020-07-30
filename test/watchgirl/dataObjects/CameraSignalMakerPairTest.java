package watchgirl.dataObjects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import watchgirl.devices.Camera;
import watchgirl.devices.SignalMaker;
import watchgirl.tools.EntropyTools;
import watchgirl.tools.HmacGenerator;
import watchgirl.tools.TimeKeeper;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertSame;

public class CameraSignalMakerPairTest {

    private Camera camera;
    private CameraSignalMakerPair cameraSignalMakerPair;
    private SignalMaker signalMaker;

    @BeforeEach
    void setup() {
        UUID uuid = UUID.randomUUID();
        String secret = "SECRET";
        TimeKeeper timeKeeper = new TimeKeeper();
        HmacGenerator hmacGenerator = new HmacGenerator();
        EntropyTools entropyTools = new EntropyTools();

        camera = new Camera(uuid, timeKeeper, entropyTools);
        signalMaker = new SignalMaker(timeKeeper, secret, hmacGenerator);
        cameraSignalMakerPair = new CameraSignalMakerPair(camera, signalMaker);
    }

    @Test
    void getCamera() {
        assertSame(camera, cameraSignalMakerPair.getCamera());
    }

    @Test
    void getSignalMaker() {
        assertSame(signalMaker, cameraSignalMakerPair.getSignalMaker());
    }
}
