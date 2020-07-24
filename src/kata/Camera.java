package kata;

import java.util.HashMap;
import java.util.Map;

public class Camera {

    private final Map<Integer, SignalOutput> capturedPhotos = new HashMap<>();
    
    public void takePhoto(int time, SignalOutput receivedSignalOutput) {
//        int time = getTime();
        System.out.println(
                String.format("[Taking photo] Time: %d, Signal: %s", time, receivedSignalOutput.toString()));
        capturedPhotos.put(time, receivedSignalOutput);
    }

//    private int getTime() {
//        return -999;
//    }

    public SignalOutput getReceivedSignalOutput(int time) {
        return capturedPhotos.get(time);
    }
}
