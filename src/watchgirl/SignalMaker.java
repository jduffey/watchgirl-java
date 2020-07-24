package watchgirl;

public class SignalMaker {

    private final TimeKeeper timeKeeper;
    private final SecretKeeper secretKeeper;
    private final CustomHasher customHasher;

    public SignalMaker(TimeKeeper timeKeeper, SecretKeeper secretKeeper, CustomHasher customHasher) {
        this.timeKeeper = timeKeeper;
        this.secretKeeper = secretKeeper;
        this.customHasher = customHasher;
    }

    public SignalOutput generateSignal() {
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

    private String getDigest() {
        String currentTime = timeKeeper.getCurrentUnixTime();
        String secret = secretKeeper.getSecret();

        return customHasher.getDigest(currentTime, secret);
    }

    private int getLastDigitMod4(String digest) {
        int lastIndexOfSha256Digest = 63;
        int hexRadix = 16;
        String lastDigit = digest.substring(lastIndexOfSha256Digest);
        int lastDigitAsInt = Integer.parseInt(lastDigit, hexRadix);

        return lastDigitAsInt % 4;
    }
}
