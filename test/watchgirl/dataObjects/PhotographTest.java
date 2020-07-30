package watchgirl.dataObjects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PhotographTest {

    private static final UUID CAMERA_ID = UUID.randomUUID();
    private static final UUID PHOTO_ID = UUID.randomUUID();
    private static final String TIME = "TIME";
    private static final SignalOutput SIGNAL = SignalOutput.RED;
    private Photograph underTest;

    @BeforeEach
    void setup() {
        underTest = new Photograph(CAMERA_ID, PHOTO_ID, TIME, SIGNAL);
    }

    @Test
    void getCameraId() {
        assertEquals(CAMERA_ID, underTest.getCameraId());
    }

    @Test
    void getTime() {
        assertEquals(TIME, underTest.getTime());
    }

    @Test
    void getSignal() {
        assertEquals(SIGNAL, underTest.getSignal());
    }

    @Test
    void getPhotoId() {
        assertEquals(PHOTO_ID, underTest.getPhotoId());
    }
}
