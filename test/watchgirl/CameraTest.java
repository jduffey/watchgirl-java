package watchgirl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;

public class CameraTest {

    private static final String TIME_0 = "TIME_0";
    private static final String TIME_1 = "TIME_1";
    private Camera camera;
    private UUID cameraId;

    @BeforeEach
    void setup() {
        cameraId = UUID.randomUUID();
        TimeKeeper timeKeeper = mock(TimeKeeper.class);
        when(timeKeeper.getCurrentUnixTime()).thenReturn(TIME_0, TIME_1);
        camera = new Camera(cameraId, timeKeeper);
    }

    @Test
    void takePhoto_storesTimeAndReceivedSignal() {
        camera.takePhoto(SignalOutput.RED);
        camera.takePhoto(SignalOutput.GREEN);

        List<Photograph> storedPhotos = camera.getStoredPhotos();

        Assertions.assertEquals(
                SignalOutput.RED,
                getSignalOfPhoto(storedPhotos, TIME_0));

        Assertions.assertEquals(
                SignalOutput.GREEN,
                getSignalOfPhoto(storedPhotos, TIME_1));
    }

    @Test
    void takePhoto_storesCameraIdWithPhoto() {
        TimeKeeper timeKeeper = mock(TimeKeeper.class);
        when(timeKeeper.getCurrentUnixTime()).thenReturn(TIME_0, TIME_1);
        Camera camera = new Camera(cameraId, timeKeeper);

        camera.takePhoto(SignalOutput.RED);

        List<Photograph> storedPhotos = camera.getStoredPhotos();

        Photograph photo = storedPhotos.stream().findFirst().orElse(null);

        Assertions.assertEquals(
                cameraId,
                photo.getCameraId()
        );
    }

    private SignalOutput getSignalOfPhoto(List<Photograph> storedPhotos, String time) {
        Photograph photograph = storedPhotos.stream()
                .filter(photo -> photo.getTime().equals(time))
                .findFirst()
                .orElse(null);

        return null == photograph ? null : photograph.getSignal();
    }
}
