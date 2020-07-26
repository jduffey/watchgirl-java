package watchgirl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class AnalyzedPhotographTest {

    private AnalyzedPhotograph underTest;
    private PhotographStatus expectedStatus;
    private Photograph photograph;

    @BeforeEach
    void setup() {
        UUID cameraId = UUID.randomUUID();
        UUID photoId = UUID.randomUUID();
        String time = "TIME";
        SignalOutput signal = SignalOutput.RED;
        photograph = new Photograph(cameraId, photoId, time, signal);
        expectedStatus = PhotographStatus.OK;

        underTest = new AnalyzedPhotograph(photograph, expectedStatus);
    }

    @Test
    void getStatus() {
        Assertions.assertEquals(expectedStatus, underTest.getStatus());
    }

    @Test
    void getPhotograph() {
        Assertions.assertSame(photograph, underTest.getPhoto());
    }
}
