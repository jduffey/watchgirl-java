package watchgirl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;

public class CameraTest {

    private static final String TIME = "TIME_0";
    private final TimeKeeper timeKeeper = mock(TimeKeeper.class);
    private Camera camera;
    private UUID cameraId;

    @BeforeEach
    void setup() {
        cameraId = UUID.randomUUID();
        camera = new Camera(cameraId, timeKeeper);
        when(timeKeeper.getCurrentUnixTime()).thenReturn(TIME);
    }

    @Test
    void takePhoto_storesTimeAndReceivedSignal() {
        camera.takePhoto(SignalOutput.RED);

        List<Photograph> storedPhotos = camera.getStoredPhotos();

        Assertions.assertEquals(SignalOutput.RED, getSignalOfPhoto(storedPhotos));
    }

    @Test
    void takePhoto_storesCameraIdWithPhoto() {
        camera.takePhoto(SignalOutput.RED);

        List<Photograph> storedPhotos = camera.getStoredPhotos();

        Photograph photo = storedPhotos.stream().findFirst().orElse(null);

        assert photo != null;
        Assertions.assertEquals(cameraId, photo.getCameraId());
    }

    private SignalOutput getSignalOfPhoto(List<Photograph> storedPhotos) {
        Photograph photograph = storedPhotos.stream()
                .filter(p -> p.getTime().equals(TIME))
                .findFirst()
                .orElse(null);

        return null == photograph ? null : photograph.getSignal();
    }
}
