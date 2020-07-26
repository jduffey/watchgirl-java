package watchgirl.devices;

import watchgirl.dataObjects.SignalOutput;
import watchgirl.tools.HmacColorMapper;
import watchgirl.tools.HmacGenerator;
import watchgirl.tools.TimeKeeper;

public class SignalMaker {

    private final TimeKeeper timeKeeper;
    private final String secret;
    private final HmacGenerator hmacGenerator;

    public SignalMaker(TimeKeeper timeKeeper, String secret, HmacGenerator hmacGenerator) {
        this.timeKeeper = timeKeeper;
        this.secret = secret;
        this.hmacGenerator = hmacGenerator;
    }

    public SignalOutput generateSignal() throws Exception {
        String hmac = getHmac();

        return HmacColorMapper.getSignal(hmac);
    }

    private String getHmac() throws Exception {
        String currentTime = timeKeeper.getCurrentUnixTime();

        return hmacGenerator.generateHmac(currentTime, secret);
    }
}
