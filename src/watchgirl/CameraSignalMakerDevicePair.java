package watchgirl;

public class CameraSignalMakerDevicePair {

    private final Camera camera;
    private final SignalMaker signalMaker;

    public CameraSignalMakerDevicePair(Camera camera, SignalMaker signalMaker) {
        this.camera = camera;
        this.signalMaker = signalMaker;
    }

    public Camera getCamera() {
        return camera;
    }

    public SignalMaker getSignalMaker() {
        return signalMaker;
    }
}
