package watchgirl;

import java.util.HashMap;
import java.util.Map;

public class Camera {

    private final Map<String, SignalOutput> capturedPhotos = new HashMap<>();
    private final TimeKeeper timeKeeper;

    public Camera(TimeKeeper timeKeeper) {
        this.timeKeeper = timeKeeper;
    }
    
    public void takePhoto(SignalOutput receivedSignalOutput) {
        String time = timeKeeper.getCurrentUnixTime();
        System.out.println(
                String.format("[Taking photo] Time: %s, Signal: %s", time, receivedSignalOutput.toString()));
        capturedPhotos.put(time, receivedSignalOutput);
    }

    public SignalOutput getReceivedSignalOutput(String time) {
        return capturedPhotos.get(time);
    }
}
