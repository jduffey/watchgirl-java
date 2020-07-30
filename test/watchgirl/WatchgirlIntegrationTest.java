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

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class WatchgirlIntegrationTest {

    private Photograph photo;
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

        List<Photograph> photographs = camera.getStoredPhotos();

        photo = photographs.stream().findFirst().orElse(null);

        assertNotNull(photo);
        analyzedPhoto = photoAnalyzer.createAnalyzedPhotograph(photo);
    }

    @Test
    void capturedPhotograph_isSamePhotographInAnalyzedPhotograph() {
        assertEquals(photo, analyzedPhoto.getPhoto());
    }

    @Test
    void analyzedPhotographStatusIsOk() {
        assertEquals(AnalyzedPhotographStatus.MATCH, analyzedPhoto.getStatus());
    }
}
