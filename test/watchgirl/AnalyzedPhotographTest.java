package watchgirl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class AnalyzedPhotographTest {

    private AnalyzedPhotograph underTest;
    private Photograph photo;
    private PhotographStatus status;

    @BeforeEach
    void setup() {
        UUID cameraId = UUID.randomUUID();
        UUID photoId = UUID.randomUUID();
        String time = "TIME";
        SignalOutput signal = SignalOutput.RED;
        photo = new Photograph(cameraId, photoId, time, signal);
        status = PhotographStatus.OK;

        underTest = new AnalyzedPhotograph(photo, status);
    }

    @Test
    void getPhotograph() {
        Assertions.assertSame(photo, underTest.getPhoto());
    }

    @Test
    void getStatus() {
        Assertions.assertEquals(status, underTest.getStatus());
    }
}
