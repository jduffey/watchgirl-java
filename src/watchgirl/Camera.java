package watchgirl;

import java.util.*;

public class Camera {

    List<PhotoData> capturedPhotos = new ArrayList<>();
    private final TimeKeeper timeKeeper;
    private final UUID cameraId = UUID.randomUUID();

    public Camera(TimeKeeper timeKeeper) {
        this.timeKeeper = timeKeeper;
    }
    
    public void takePhoto(SignalOutput receivedSignalOutput) {
        String time = timeKeeper.getCurrentUnixTime();
        PhotoData photoData = new PhotoData(cameraId, time, receivedSignalOutput);
        capturedPhotos.add(photoData);
    }

    public List<PhotoData> getStoredPhotos() {
        return capturedPhotos;
    }
}
