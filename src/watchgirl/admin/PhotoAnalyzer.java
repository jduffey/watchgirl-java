package watchgirl.admin;

import watchgirl.dataObjects.AnalyzedPhotograph;
import watchgirl.dataObjects.Photograph;
import watchgirl.dataObjects.PhotographStatus;
import watchgirl.dataObjects.SignalOutput;
import watchgirl.tools.HmacColorMapper;
import watchgirl.tools.HmacGenerator;

import java.util.UUID;

public class PhotoAnalyzer {

    private final HmacGenerator hmacGenerator;
    private final SecretKeeper secretKeeper;

    public PhotoAnalyzer(HmacGenerator hmacGenerator, SecretKeeper secretKeeper){
        this.hmacGenerator = hmacGenerator;
        this.secretKeeper = secretKeeper;
    }

    public AnalyzedPhotograph createAnalyzedPhotograph(Photograph photograph) throws Exception {
        PhotographStatus status =
                getExpectedSignal(photograph) == photograph.getSignal()
                ? PhotographStatus.OK
                : PhotographStatus.BAD;

        return new AnalyzedPhotograph(photograph, status);
    }

    private SignalOutput getExpectedSignal(Photograph photo) throws Exception {
        UUID cameraID = photo.getCameraId();
        String time = photo.getTime();
        String secret = secretKeeper.getSecret(cameraID);

        return generateSignal(time, secret);
    }

    private SignalOutput generateSignal(String message, String secret) throws Exception {
        String hmac = hmacGenerator.generateHmac(message, secret);

        return HmacColorMapper.getSignal(hmac);
    }
}
