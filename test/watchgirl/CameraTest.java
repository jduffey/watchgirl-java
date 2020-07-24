package watchgirl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CameraTest {

    private static final String TIME_0 = "TIME_0";
    private static final String TIME_1 = "TIME_1";

    @Test
    public void takePhoto_storesTimeAndReceivedSignal() {
        TimeKeeper timeKeeper = mock(TimeKeeper.class);
        when(timeKeeper.getCurrentUnixTime()).thenReturn(TIME_0, TIME_1);
        Camera camera = new Camera(timeKeeper);

        camera.takePhoto(SignalOutput.RED);
        camera.takePhoto(SignalOutput.GREEN);

        List<PhotoData> storedPhotos = camera.getStoredPhotos();

        Assertions.assertEquals(
                SignalOutput.RED,
                getSignal(storedPhotos, TIME_0));

        Assertions.assertEquals(
                SignalOutput.GREEN,
                getSignal(storedPhotos, TIME_1));
    }

    private SignalOutput getSignal(List<PhotoData> storedPhotos, String time) {
        PhotoData photoData = storedPhotos.stream()
                .filter(photo -> photo.getTime().equals(time))
                .findFirst()
                .orElse(null);

        return null == photoData ? null : photoData.getSignal();
    }
}
