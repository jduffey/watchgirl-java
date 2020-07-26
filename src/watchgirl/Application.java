package watchgirl;

import java.util.List;

public class Application {

    public static final int NUM_PHOTOS_TO_TAKE = 5;
    public static final int SECONDS_BETWEEN_PHOTOS = 1;

    public static void main(String[] args) throws Exception {
        System.out.println("*** START ***");

        CameraSignalMakerPair pair = createCameraAndSignalMaker();
        Camera camera = pair.getCamera();
        SignalMaker signalMaker = pair.getSignalMaker();
        TimeKeeper timeKeeper = new TimeKeeper();
        HmacGenerator hmacGenerator = new HmacGenerator();
        SecretKeeper secretKeeper = SecretKeeper.getInstance();
        PhotoAnalyzer photoAnalyzer = new PhotoAnalyzer(hmacGenerator, secretKeeper);

        takePhotos(camera, signalMaker, timeKeeper, NUM_PHOTOS_TO_TAKE);

        List<Photograph> photos = camera.getStoredPhotos();

        printPhotosInfo(photos);

        analyzePhotos(photoAnalyzer, photos);
    }

    private static void analyzePhotos(PhotoAnalyzer photoAnalyzer, List<Photograph> photos) throws Exception {
        System.out.println("\n*** Analyzing Photos ***");
        for(Photograph photo : photos) {
            SignalOutput expectedSignal = photoAnalyzer.getExpectedSignal(photo);
            String status = expectedSignal == photo.getSignal() ? "OK" : "BAD";
            System.out.println(String.format("%s - %s", status, photo.getPhotoId()));
        }
    }

    private static void printPhotosInfo(List<Photograph> photos) {
        System.out.println("\n*** Photos Info ***");
        for (Photograph photo : photos) {
            String logMessage = String.format(
                    "Time: %s, Signal: %s, PhotoId: %s", photo.getTime(), photo.getSignal(), photo.getPhotoId());
            System.out.println(logMessage);
        }
    }

    private static void takePhotos(Camera camera, SignalMaker signalMaker, TimeKeeper timeKeeper, int numPhotosToTake) throws Exception {
        System.out.println("\n*** Creating Signals ***");
        for (int i = 0; i < numPhotosToTake; i++) {
            SignalOutput emittedSignal = signalMaker.generateSignal();
            System.out.println(String.format("Time: %s, Signal: %s", timeKeeper.getCurrentUnixTime(), emittedSignal));
            camera.takePhoto(emittedSignal);
            Thread.sleep(SECONDS_BETWEEN_PHOTOS * 1000);
        }
    }

    private static CameraSignalMakerPair createCameraAndSignalMaker() {
        EntropyTools entropyTools = new EntropyTools();
        SecretKeeper secretKeeper = SecretKeeper.getInstance();
        EquipmentProvisioner equipmentProvisioner = new EquipmentProvisioner(entropyTools, secretKeeper);

        return equipmentProvisioner.createCameraSignalMakerPair();
    }
}
