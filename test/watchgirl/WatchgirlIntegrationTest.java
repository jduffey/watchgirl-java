package watchgirl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import watchgirl.admin.EquipmentProvisioner;
import watchgirl.admin.PhotoAnalyzer;
import watchgirl.admin.SecretKeeper;
import watchgirl.dataObjects.*;
import watchgirl.devices.Camera;
import watchgirl.devices.SignalMaker;
import watchgirl.tools.EntropyTools;
import watchgirl.tools.HmacGenerator;

import java.util.List;

public class WatchgirlIntegrationTest {

    private Photograph photo;
    private AnalyzedPhotograph analyzedPhoto;

    @BeforeEach
    void e2e_soFar() {
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

        photo = photographs.stream().findFirst().orElse(null);

        assert photo != null;
        analyzedPhoto = photoAnalyzer.createAnalyzedPhotograph(photo);
    }

    @Test
    void capturedPhotograph_isSamePhotographInAnalyzedPhotograph() {
        Assertions.assertEquals(photo, analyzedPhoto.getPhoto());
    }

    @Test
    void analyzedPhotographStatusIsOk() {
        Assertions.assertEquals(PhotographStatus.OK, analyzedPhoto.getStatus());
    }
}
