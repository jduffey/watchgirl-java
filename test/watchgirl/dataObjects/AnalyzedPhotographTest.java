package watchgirl.dataObjects;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class AnalyzedPhotographTest {

    private static final UUID CAMERA_ID = UUID.randomUUID();
    private static final UUID PHOTO_ID = UUID.randomUUID();
    private static final SignalOutput SIGNAL = SignalOutput.RED;
    private static final String TIME = "TIME";
    private AnalyzedPhotograph underTest;
    private AnalyzedPhotographStatus status;
    private Photograph photo;

    @BeforeEach
    void setup() {
        photo = new Photograph(CAMERA_ID, PHOTO_ID, TIME, SIGNAL);
        status = AnalyzedPhotographStatus.MATCH;

        underTest = new AnalyzedPhotograph(photo, status);
    }

    @Test
    void getPhoto() {
        Assertions.assertEquals(photo, underTest.getPhoto());
    }

    @Test
    void getStatus() {
        Assertions.assertEquals(status, underTest.getStatus());
    }

    @Test
    void getCameraId() {
        Assertions.assertEquals(CAMERA_ID, underTest.getCameraId());
    }

    @Test
    void getPhotoId() {
        Assertions.assertEquals(PHOTO_ID, underTest.getPhotoId());
    }

    @Test
    void getTime() {
        Assertions.assertEquals(TIME, underTest.getTime());
    }

    @Test
    void getSignal() {
        Assertions.assertEquals(SIGNAL, underTest.getSignal());
    }
}
