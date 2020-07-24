package watchgirl;

import java.util.UUID;

public class PhotoData {

    private UUID cameraId;
    private String time;
    private SignalOutput signal;

    public PhotoData(UUID cameraId, String time, SignalOutput signal) {
        this.cameraId = cameraId;
        this.time = time;
        this.signal = signal;
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
}
