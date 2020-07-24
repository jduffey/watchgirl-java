package watchgirl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class PhotoDataTest {

    private static final UUID CAMERA_ID = UUID.randomUUID();
    private static final String TIME = "TIME";
    private static final SignalOutput SIGNAL = SignalOutput.RED;
    private PhotoData underTest;

    @BeforeEach
    void setup() {
        underTest = new PhotoData(CAMERA_ID, TIME, SIGNAL);
    }

    @Test
    void getCameraId() {
        Assertions.assertEquals(
                CAMERA_ID,
                underTest.getCameraId()
        );
    }

    @Test
    void getTime() {
        Assertions.assertEquals(
                TIME,
                underTest.getTime()
        );
    }

    @Test
    void getSignal() {
        Assertions.assertEquals(
                SIGNAL,
                underTest.getSignal()
        );
    }
}
