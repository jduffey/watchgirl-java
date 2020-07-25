package watchgirl;

import java.util.UUID;

public class Photograph {

    private final UUID cameraId;
    private final String time;
    private final SignalOutput signal;

    public Photograph(UUID cameraId, String time, SignalOutput signal) {
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
