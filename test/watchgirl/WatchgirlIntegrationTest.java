package watchgirl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import watchgirl.admin.DevicesProvisioner;
import watchgirl.admin.PhotoAnalyzer;
import watchgirl.admin.SecretKeeper;
import watchgirl.dataObjects.*;
import watchgirl.devices.Camera;
import watchgirl.devices.SignalMaker;
import watchgirl.tools.EntropyTools;
import watchgirl.tools.HmacGenerator;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WatchgirlIntegrationTest {

    private AnalyzedPhotograph analyzedPhoto;

    @BeforeEach
    void e2e_soFar() {
        EntropyTools entropyTools = new EntropyTools();
        SecretKeeper secretKeeper = SecretKeeper.getInstance();
        DevicesProvisioner devicesProvisioner = new DevicesProvisioner(entropyTools, secretKeeper);
        PhotoAnalyzer photoAnalyzer = new PhotoAnalyzer(new HmacGenerator(), secretKeeper);

        CameraSignalMakerPair cameraSignalMakerPair = devicesProvisioner.createCameraSignalMakerPair();

        Camera camera = cameraSignalMakerPair.getCamera();
        SignalMaker signalMaker = cameraSignalMakerPair.getSignalMaker();

        SignalOutput signalOutput = signalMaker.generateSignal();
        camera.takePhoto(signalOutput);

        Photograph photo = Objects.requireNonNull(camera.getStoredPhotos().stream()
                .findFirst()
                .orElse(null),
                "Camera did not have a Photograph");

        analyzedPhoto = photoAnalyzer.createAnalyzedPhotograph(photo);
    }

    @Test
    void analyzedPhotographStatusIsMatch() {
        assertEquals(AnalyzedPhotographStatus.MATCH, analyzedPhoto.getStatus());
    }
}
