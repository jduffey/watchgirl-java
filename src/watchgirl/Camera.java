package watchgirl;

import java.util.*;

public class Camera {

    private final List<Photograph> capturedPhotos = new ArrayList<>();
    private final TimeKeeper timeKeeper;
    private final UUID cameraId;

    public Camera(UUID cameraId, TimeKeeper timeKeeper) {
        this.cameraId = cameraId;
        this.timeKeeper = timeKeeper;
    }
    
    public void takePhoto(SignalOutput receivedSignalOutput) {
        String time = timeKeeper.getCurrentUnixTime();
        Photograph photograph = new Photograph(cameraId, time, receivedSignalOutput);
        capturedPhotos.add(photograph);
    }

    public List<Photograph> getStoredPhotos() {
        return capturedPhotos;
    }
}
