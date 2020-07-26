package watchgirl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.mockito.Mockito.*;

public class EquipmentProvisionerTest {

    @Test
    void createCameraSignalMakerPair() {
        UUID cameraUuid = UUID.randomUUID();
        String secret = "SECRET";

        EntropyTools entropyTools = mock(EntropyTools.class);
        SecretKeeper secretKeeper = mock(SecretKeeper.class);
        when(entropyTools.generateUuid()).thenReturn(cameraUuid);
        when(entropyTools.generateSecretKey()).thenReturn(secret);

        EquipmentProvisioner underTest = new EquipmentProvisioner(entropyTools, secretKeeper);

        CameraSignalMakerPair actual = underTest.createCameraSignalMakerPair();

        Camera camera = actual.getCamera();
        SignalMaker signalMaker = actual.getSignalMaker();

        Assertions.assertNotNull(camera);
        Assertions.assertNotNull(signalMaker);
        verify(secretKeeper).registerCameraSecret(eq(cameraUuid), eq(secret));
        verifyNoMoreInteractions(secretKeeper);
    }
}
