package watchgirl;

public class SignalMakerDevice {

    private final TimeKeeper timeKeeper;
    private final String secret;
    private final HmacGenerator hmacGenerator;

    public SignalMakerDevice(TimeKeeper timeKeeper, String secret, HmacGenerator hmacGenerator) {
        this.timeKeeper = timeKeeper;
        this.secret = secret;
        this.hmacGenerator = hmacGenerator;
    }

    public SignalOutput generateSignal() throws Exception {
        String digest = getDigest();

        int lastDigitMod4 = getLastDigitMod4(digest);

        return switch (lastDigitMod4) {
            case 0 -> SignalOutput.RED;
            case 1 -> SignalOutput.GREEN;
            case 2 -> SignalOutput.BLUE;
            case 3 -> SignalOutput.WHITE;
            default -> SignalOutput.SHOULD_NEVER_SEE_ME;
        };
    }

    private String getDigest() throws Exception {
        String currentTime = timeKeeper.getCurrentUnixTime();

        return hmacGenerator.generateHmac(currentTime, secret);
    }

    private int getLastDigitMod4(String digest) {
        int lastIndexOfSha256Digest = 63;
        int hexRadix = 16;
        String lastDigit = digest.substring(lastIndexOfSha256Digest);
        int lastDigitAsInt = Integer.parseInt(lastDigit, hexRadix);

        return lastDigitAsInt % 4;
    }
}