package watchgirl.devices;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import watchgirl.dataObjects.Photograph;
import watchgirl.dataObjects.SignalOutput;
import watchgirl.tools.EntropyTools;
import watchgirl.tools.TimeKeeper;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CameraTest {

    private static final String TIME = "TIME";
    private static final UUID CAMERA_ID = UUID.randomUUID();
    private static final UUID PHOTO_ID = UUID.randomUUID();
    private static final SignalOutput SIGNAL_OUTPUT = SignalOutput.RED;

    private static final EntropyTools entropyTools = mock(EntropyTools.class);
    private static final TimeKeeper timeKeeper = mock(TimeKeeper.class);

    private Photograph photo;

    @BeforeEach
    void setup() {
        Camera underTest = new Camera(CAMERA_ID, timeKeeper, entropyTools);
        when(timeKeeper.getCurrentUnixTime()).thenReturn(TIME);
        when(entropyTools.generateUuid()).thenReturn(PHOTO_ID);

        underTest.takePhoto(SIGNAL_OUTPUT);

        List<Photograph> photographs = underTest.getStoredPhotos();
        photo = photographs.stream().findFirst().orElse(null);
        assert photo != null;
    }

    @Test
    void takePhoto_storesCameraIdWithPhoto() {
        assertEquals(CAMERA_ID, photo.getCameraId());
    }

    @Test
    void takePhoto_storesPhotoIdWithPhoto() {
        assertEquals(PHOTO_ID, photo.getPhotoId());
    }

    @Test
    void takePhoto_storesTimeWithPhoto() {
        assertEquals(TIME, photo.getTime());
    }

    @Test
    void takePhoto_storesReceivedSignalWithPhoto() {
        assertEquals(SIGNAL_OUTPUT, photo.getSignal());
    }
}
