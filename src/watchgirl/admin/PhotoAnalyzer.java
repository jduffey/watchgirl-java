package watchgirl.admin;

import watchgirl.dataObjects.AnalyzedPhotograph;
import watchgirl.dataObjects.Photograph;
import watchgirl.dataObjects.AnalyzedPhotographStatus;
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

    public AnalyzedPhotograph createAnalyzedPhotograph(Photograph photograph) {
        if (SignalOutput.SIGNAL_ERROR == photograph.getSignal()) {
            return new AnalyzedPhotograph(photograph, AnalyzedPhotographStatus.SIGNAL_ERROR);
        }

        try {
            AnalyzedPhotographStatus status =
                getExpectedSignal(photograph) == photograph.getSignal()
                ? AnalyzedPhotographStatus.MATCH
                : AnalyzedPhotographStatus.NO_MATCH;

            return new AnalyzedPhotograph(photograph, status);
        } catch (Exception e) {
            return new AnalyzedPhotograph(photograph, AnalyzedPhotographStatus.ANALYZE_ERROR);
        }
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
