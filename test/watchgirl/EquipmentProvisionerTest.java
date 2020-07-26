package watchgirl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class EquipmentProvisionerTest {

    @Test
    void provisionCameraAndSignalMakerPair() {
        SecretKeeper secretKeeper = mock(SecretKeeper.class);
        EntropyTools entropyTools = new EntropyTools();
        EquipmentProvisioner underTest = new EquipmentProvisioner(entropyTools, secretKeeper);

        List<Object> actual = underTest.createCameraSignalMakerPair();

        Camera camera = (Camera) actual.get(0);
        SignalMakerDevice signalMakerDevice = (SignalMakerDevice) actual.get(1);

        Assertions.assertNotNull(camera);
        Assertions.assertNotNull(signalMakerDevice);
        verify(secretKeeper).registerCameraSecret(any(UUID.class), anyString());
        verifyNoMoreInteractions(secretKeeper);
    }
}
