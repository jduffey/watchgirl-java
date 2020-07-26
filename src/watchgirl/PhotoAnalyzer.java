package watchgirl;

import java.util.UUID;

public class PhotoAnalyzer {

    private final HmacGenerator hmacGenerator;
    private final SecretKeeper secretKeeper;

    public PhotoAnalyzer(HmacGenerator hmacGenerator, SecretKeeper secretKeeper){
        this.hmacGenerator = hmacGenerator;
        this.secretKeeper = secretKeeper;
    };

    public SignalOutput getExpectedSignal(Photograph photo) throws Exception {
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
