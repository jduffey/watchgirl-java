package watchgirl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class WatchgirlIntegrationTest {

    @Test
    void e2e_soFar() throws Exception {
        EntropyTools entropyTools = new EntropyTools();
        SecretKeeper secretKeeper = SecretKeeper.getInstance();
        EquipmentProvisioner equipmentProvisioner = new EquipmentProvisioner(entropyTools, secretKeeper);
        PhotoAnalyzer photoAnalyzer = new PhotoAnalyzer(new HmacGenerator(), secretKeeper);

        CameraSignalMakerPair cameraSignalMakerPair = equipmentProvisioner.createCameraSignalMakerPair();

        Camera camera = cameraSignalMakerPair.getCamera();
        SignalMaker signalMaker = cameraSignalMakerPair.getSignalMaker();

        SignalOutput signalOutput = signalMaker.generateSignal();
        camera.takePhoto(signalOutput);

        List<Photograph> photographs = camera.getStoredPhotos();

        Photograph photograph = photographs.stream().findFirst().orElse(null);

        assert photograph != null;
        SignalOutput expectedSignal = photoAnalyzer.getExpectedSignal(photograph);

        Assertions.assertEquals(expectedSignal, signalOutput);
    }
}
