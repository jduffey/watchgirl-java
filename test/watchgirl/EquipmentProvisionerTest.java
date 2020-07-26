package watchgirl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class EquipmentProvisionerTest {

    @Test
    void provisionCameraAndSignalMakerPair() {
        EntropyTools entropyTools = new EntropyTools();
        SecretKeeper secretKeeper = mock(SecretKeeper.class);
        EquipmentProvisioner underTest = new EquipmentProvisioner(entropyTools, secretKeeper);

        CameraSignalMakerDevicePair actual = underTest.createCameraSignalMakerPair();

        Camera camera = actual.getCamera();
        SignalMaker signalMaker = actual.getSignalMaker();

        Assertions.assertNotNull(camera);
        Assertions.assertNotNull(signalMaker);
        verify(secretKeeper).registerCameraSecret(any(UUID.class), anyString());
        verifyNoMoreInteractions(secretKeeper);
    }
}
