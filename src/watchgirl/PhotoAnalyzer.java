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

        int lastDigitMod4 = getLastDigitMod4(hmac);

        return switch (lastDigitMod4) {
            case 0 -> SignalOutput.RED;
            case 1 -> SignalOutput.GREEN;
            case 2 -> SignalOutput.BLUE;
            case 3 -> SignalOutput.WHITE;
            default -> SignalOutput.SHOULD_NEVER_SEE_ME;
        };
    }

    private int getLastDigitMod4(String digest) {
        int lastIndexOfSha256Digest = 63;
        int hexRadix = 16;
        String lastDigit = digest.substring(lastIndexOfSha256Digest);
        int lastDigitAsInt = Integer.parseInt(lastDigit, hexRadix);

        return lastDigitAsInt % 4;
    }
}
