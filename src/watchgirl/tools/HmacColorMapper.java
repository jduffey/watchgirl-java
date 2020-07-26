package watchgirl.tools;

import watchgirl.dataObjects.SignalOutput;

public class HmacColorMapper {

    public static SignalOutput getSignal(String input) {

        int inputMod4 = getLastDigitMod4(input);

        return switch (inputMod4) {
            case 0 -> SignalOutput.RED;
            case 1 -> SignalOutput.GREEN;
            case 2 -> SignalOutput.BLUE;
            case 3 -> SignalOutput.WHITE;
            default -> SignalOutput.SHOULD_NEVER_SEE_ME;
        };
    }

    private static int getLastDigitMod4(String input) {
        int lastIndexOfSha256Digest = 63;
        int hexRadix = 16;
        String lastDigit = input.substring(lastIndexOfSha256Digest);
        int lastDigitAsInt = Integer.parseInt(lastDigit, hexRadix);

        return lastDigitAsInt % 4;
    }
}
