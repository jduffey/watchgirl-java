package watchgirl.devices;

import watchgirl.dataObjects.Photograph;
import watchgirl.dataObjects.SignalOutput;
import watchgirl.tools.EntropyTools;
import watchgirl.tools.TimeKeeper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Camera {

    private final List<Photograph> capturedPhotos = new ArrayList<>();
    private final TimeKeeper timeKeeper;
    private final UUID cameraId;
    private final EntropyTools entropyTools;

    public Camera(UUID cameraId, TimeKeeper timeKeeper, EntropyTools entropyTools) {
        this.cameraId = cameraId;
        this.timeKeeper = timeKeeper;
        this.entropyTools = entropyTools;
    }
    
    public void takePhoto(SignalOutput receivedSignalOutput) {
        String time = timeKeeper.getCurrentUnixTime();
        UUID photoId = entropyTools.generateUuid();
        Photograph photograph = new Photograph(cameraId, photoId, time, receivedSignalOutput);
        capturedPhotos.add(photograph);
    }

    public List<Photograph> getStoredPhotos() {
        return capturedPhotos;
    }
}
