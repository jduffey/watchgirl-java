package watchgirl;

public class CameraSignalMakerPair {

    private final Camera camera;
    private final SignalMaker signalMaker;

    public CameraSignalMakerPair(Camera camera, SignalMaker signalMaker) {
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
