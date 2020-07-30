package watchgirl.devices;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import watchgirl.dataObjects.SignalOutput;
import watchgirl.tools.HmacGenerator;
import watchgirl.tools.TimeKeeper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SignalMakerTest {

    private static final String DIGEST_FIRST_63_DIGITS =
            "000000000000000000000000000000000000000000000000000000000000000";
    private static final String CURRENT_TIME = "CURRENT_TIME";
    private static final String SECRET = "SECRET";
    private final TimeKeeper timeKeeper = mock(TimeKeeper.class);
    private final HmacGenerator hmacGenerator = mock(HmacGenerator.class);
    private SignalMaker underTest;

    @BeforeEach
    void setup() {
        underTest = new SignalMaker(timeKeeper, SECRET, hmacGenerator);
        when(timeKeeper.getCurrentUnixTime()).thenReturn(CURRENT_TIME);
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "4", "8", "c"})
    void generateSignal_givenDigestMod4Equals0_returnsRed(String value) throws Exception {
        when(hmacGenerator.generateHmac(CURRENT_TIME, SECRET))
                .thenReturn(DIGEST_FIRST_63_DIGITS + value);

        assertEquals(SignalOutput.RED, underTest.generateSignal());
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "5", "9", "d"})
    void generateSignal_givenDigestMod4Equals1_returnsGreen(String value) throws Exception {
        when(hmacGenerator.generateHmac(CURRENT_TIME, SECRET))
                .thenReturn(DIGEST_FIRST_63_DIGITS + value);

        assertEquals(SignalOutput.GREEN, underTest.generateSignal());
    }

    @ParameterizedTest
    @ValueSource(strings = {"2", "6", "a", "e"})
    void generateSignal_givenDigestMod4Equals2_returnsBlue(String value) throws Exception {
        when(hmacGenerator.generateHmac(CURRENT_TIME, SECRET))
                .thenReturn(DIGEST_FIRST_63_DIGITS + value);

        assertEquals(SignalOutput.BLUE, underTest.generateSignal());
    }

    @ParameterizedTest
    @ValueSource(strings = {"3", "7", "b", "f"})
    void generateSignal_givenDigestMod4Equals3_returnsWhite(String value) throws Exception {
        when(hmacGenerator.generateHmac(CURRENT_TIME, SECRET))
                .thenReturn(DIGEST_FIRST_63_DIGITS + value);

        assertEquals(SignalOutput.WHITE, underTest.generateSignal());
    }

    @Test
    void generateSignal_throwsException_returnsErrorSignal() throws Exception {
        doThrow(new Exception()).when(hmacGenerator).generateHmac(anyString(), anyString());

        assertEquals(SignalOutput.SIGNAL_ERROR, underTest.generateSignal());
    }
}
