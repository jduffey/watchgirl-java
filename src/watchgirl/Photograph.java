package watchgirl;

import java.util.UUID;

public class Photograph {

    private final UUID cameraId;
    private final UUID photoId;
    private final String time;
    private final SignalOutput signal;

    public Photograph(UUID cameraId, UUID photoId, String time, SignalOutput signal) {
        this.cameraId = cameraId;
        this.time = time;
        this.signal = signal;
        this.photoId = photoId;
    }

    public UUID getCameraId() {
        return cameraId;
    }

    public String getTime() {
        return time;
    }

    public SignalOutput getSignal() {
        return signal;
    }

    public UUID getPhotoId() {
        return photoId;
    }
}
