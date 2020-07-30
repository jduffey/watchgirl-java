package watchgirl;

import watchgirl.admin.DevicesProvisioner;
import watchgirl.admin.PhotoAnalyzer;
import watchgirl.admin.SecretKeeper;
import watchgirl.dataObjects.AnalyzedPhotograph;
import watchgirl.dataObjects.CameraSignalMakerPair;
import watchgirl.dataObjects.Photograph;
import watchgirl.dataObjects.SignalOutput;
import watchgirl.devices.Camera;
import watchgirl.devices.SignalMaker;
import watchgirl.tools.EntropyTools;
import watchgirl.tools.HmacGenerator;
import watchgirl.tools.TimeKeeper;

import java.util.List;

public class Application {

    public static final int NUM_PHOTOS_TO_TAKE = 10;
    public static final int SECONDS_BETWEEN_PHOTOS = 1;

    public static void main(String[] args) throws Exception {
        System.out.println("*** START ***");

        CameraSignalMakerPair pair = createCameraAndSignalMaker();

        Camera camera = pair.getCamera();
        SignalMaker signalMaker = pair.getSignalMaker();

        PhotoAnalyzer photoAnalyzer = createPhotoAnalyzer();

        takePhotos(camera, signalMaker);

        List<Photograph> photos = camera.getStoredPhotos();

        analyzePhotos(photoAnalyzer, photos);
    }

    private static void analyzePhotos(PhotoAnalyzer photoAnalyzer, List<Photograph> photos) {
        System.out.println("\n*** Analyzing Photos ***");

        for(Photograph photo : photos) {
            AnalyzedPhotograph analyzedPhotograph = photoAnalyzer.createAnalyzedPhotograph(photo);
            System.out.printf("%s - %s%n", analyzedPhotograph.getStatus(), analyzedPhotograph.getPhotoId());
        }
    }

    private static void takePhotos(Camera camera, SignalMaker signalMaker) throws Exception {
        System.out.println("\n*** Creating Signals ***");
        TimeKeeper timeKeeper = new TimeKeeper();

        for (int i = 0; i < NUM_PHOTOS_TO_TAKE; i++) {
            SignalOutput emittedSignal = signalMaker.generateSignal();
            System.out.printf("Time: %s, Signal: %s%n", timeKeeper.getCurrentUnixTime(), emittedSignal);
            camera.takePhoto(emittedSignal);
            Thread.sleep(SECONDS_BETWEEN_PHOTOS * 1000);
        }
    }

    private static CameraSignalMakerPair createCameraAndSignalMaker() {
        EntropyTools entropyTools = new EntropyTools();
        SecretKeeper secretKeeper = SecretKeeper.getInstance();
        DevicesProvisioner devicesProvisioner = new DevicesProvisioner(entropyTools, secretKeeper);

        return devicesProvisioner.createCameraSignalMakerPair();
    }

    private static PhotoAnalyzer createPhotoAnalyzer() {
        HmacGenerator hmacGenerator = new HmacGenerator();
        SecretKeeper secretKeeper = SecretKeeper.getInstance();

        return new PhotoAnalyzer(hmacGenerator, secretKeeper);
    }
}
