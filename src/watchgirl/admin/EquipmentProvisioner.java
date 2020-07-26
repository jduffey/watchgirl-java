package watchgirl.admin;

import watchgirl.devices.Camera;
import watchgirl.devices.SignalMaker;
import watchgirl.dataObjects.CameraSignalMakerPair;
import watchgirl.tools.EntropyTools;
import watchgirl.tools.HmacGenerator;
import watchgirl.tools.TimeKeeper;

import java.util.UUID;

public class EquipmentProvisioner {

    private final EntropyTools entropyTools;
    private final SecretKeeper secretKeeper;

    public EquipmentProvisioner(EntropyTools entropyTools, SecretKeeper secretKeeper) {
        this.entropyTools = entropyTools;
        this.secretKeeper = secretKeeper;
    }

    public CameraSignalMakerPair createCameraSignalMakerPair() {
        UUID cameraId = entropyTools.generateUuid();
        String secret = entropyTools.generateSecretKey();

        secretKeeper.registerCameraSecret(cameraId, secret);

        Camera camera = new Camera(cameraId, new TimeKeeper(), new EntropyTools());
        SignalMaker signalMaker = new SignalMaker(new TimeKeeper(), secret, new HmacGenerator());

        return new CameraSignalMakerPair(camera, signalMaker);
    }
}
