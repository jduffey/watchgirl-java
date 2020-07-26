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

        CameraSignalMakerDevicePair cameraSignalMakerDevicePair = equipmentProvisioner.createCameraSignalMakerPair();

        Camera camera = cameraSignalMakerDevicePair.getCamera();
        SignalMakerDevice signalMakerDevice = cameraSignalMakerDevicePair.getSignalMakerDevice();

        SignalOutput signalOutput = signalMakerDevice.generateSignal();
        camera.takePhoto(signalOutput);

        List<Photograph> photographs = camera.getStoredPhotos();

        Photograph photograph = photographs.stream().findFirst().orElse(null);

        assert photograph != null;
        SignalOutput expectedSignal = photoAnalyzer.getExpectedSignal(photograph);

        Assertions.assertEquals(expectedSignal, signalOutput);
    }
}
