package watchgirl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SignalMakerDeviceTest {

    private static final String DIGEST_FIRST_63_DIGITS =
            "6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4";
    private static final String CURRENT_TIME = "CURRENT_TIME";
    private static final String SECRET = "SECRET";
    private final TimeKeeper timeKeeper = mock(TimeKeeper.class);
    private final HmacGenerator hmacGenerator = mock(HmacGenerator.class);
    private SignalMakerDevice underTest;

    @BeforeEach
    void setup() {
        underTest = new SignalMakerDevice(timeKeeper, SECRET, hmacGenerator);
        when(timeKeeper.getCurrentUnixTime()).thenReturn(CURRENT_TIME);
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "4", "8", "c"})
    void generateSignal_givenDigestMod4Equals0_returnsRed(String value) throws Exception {
        when(hmacGenerator.generateHmac(CURRENT_TIME, SECRET))
                .thenReturn(DIGEST_FIRST_63_DIGITS + value);

        Assertions.assertEquals(
                SignalOutput.RED,
                underTest.generateSignal());
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "5", "9", "d"})
    void generateSignal_givenDigestMod4Equals1_returnsGreen(String value) throws Exception {
        when(hmacGenerator.generateHmac(CURRENT_TIME, SECRET))
                .thenReturn(DIGEST_FIRST_63_DIGITS + value);

        Assertions.assertEquals(
                SignalOutput.GREEN,
                underTest.generateSignal());
    }

    @ParameterizedTest
    @ValueSource(strings = {"2", "6", "a", "e"})
    void generateSignal_givenDigestMod4Equals2_returnsBlue(String value) throws Exception {
        when(hmacGenerator.generateHmac(CURRENT_TIME, SECRET))
                .thenReturn(DIGEST_FIRST_63_DIGITS + value);

        Assertions.assertEquals(
                SignalOutput.BLUE,
                underTest.generateSignal());
    }

    @ParameterizedTest
    @ValueSource(strings = {"3", "7", "b", "f"})
    void generateSignal_givenDigestMod4Equals3_returnsWhite(String value) throws Exception {
        when(hmacGenerator.generateHmac(CURRENT_TIME, SECRET))
                .thenReturn(DIGEST_FIRST_63_DIGITS + value);

        Assertions.assertEquals(
                SignalOutput.WHITE,
                underTest.generateSignal());
    }
}
