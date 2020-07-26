package watchgirl;

public class CameraSignalMakerDevicePair {

    private final Camera camera;
    private final SignalMakerDevice signalMakerDevice;

    public CameraSignalMakerDevicePair(Camera camera, SignalMakerDevice signalMakerDevice) {
        this.camera = camera;
        this.signalMakerDevice = signalMakerDevice;
    }

    public Camera getCamera() {
        return camera;
    }

    public SignalMakerDevice getSignalMakerDevice() {
        return signalMakerDevice;
    }
}
