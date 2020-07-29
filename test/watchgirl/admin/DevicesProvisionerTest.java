package watchgirl.admin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import watchgirl.dataObjects.CameraSignalMakerPair;
import watchgirl.devices.Camera;
import watchgirl.devices.SignalMaker;
import watchgirl.tools.EntropyTools;

import java.util.UUID;

import static org.mockito.Mockito.*;

public class DevicesProvisionerTest {

    @Test
    void createCameraSignalMakerPair() {
        UUID cameraUuid = UUID.randomUUID();
        String secret = "SECRET";

        EntropyTools entropyTools = mock(EntropyTools.class);
        SecretKeeper secretKeeper = mock(SecretKeeper.class);
        when(entropyTools.generateUuid()).thenReturn(cameraUuid);
        when(entropyTools.generateSecretKey()).thenReturn(secret);

        DevicesProvisioner underTest = new DevicesProvisioner(entropyTools, secretKeeper);

        CameraSignalMakerPair actual = underTest.createCameraSignalMakerPair();

        Camera camera = actual.getCamera();
        SignalMaker signalMaker = actual.getSignalMaker();

        Assertions.assertNotNull(camera);
        Assertions.assertNotNull(signalMaker);
        verify(secretKeeper).registerCameraSecret(eq(cameraUuid), eq(secret));
        verifyNoMoreInteractions(secretKeeper);
    }
}
