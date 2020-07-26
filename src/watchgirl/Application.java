package watchgirl;

import java.util.List;

public class Application {

    public static void main(String[] args) throws Exception {
        System.out.println("*** START ***");

        CameraSignalMakerPair pair = createCameraAndSignalMaker();
        Camera camera = pair.getCamera();
        SignalMaker signalMaker = pair.getSignalMaker();

        while (true) {
            cycle(camera, signalMaker);
            Thread.sleep(3000);
        }
    }

    private static void cycle(Camera camera, SignalMaker signalMaker) throws Exception {
        SignalOutput emittedSignal = signalMaker.generateSignal();
        System.out.println(emittedSignal);

        camera.takePhoto(emittedSignal);

        List<Photograph> photos = camera.getStoredPhotos();

        for (Photograph photo : photos) {
            System.out.println(""+photo.getTime()+": "+photo.getSignal());
        }
    }

    private static CameraSignalMakerPair createCameraAndSignalMaker() {
        EntropyTools entropyTools = new EntropyTools();
        SecretKeeper secretKeeper = SecretKeeper.getInstance();
        EquipmentProvisioner equipmentProvisioner = new EquipmentProvisioner(entropyTools, secretKeeper);

        return equipmentProvisioner.createCameraSignalMakerPair();
    }
}
