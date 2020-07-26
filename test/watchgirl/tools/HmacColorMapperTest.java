package watchgirl.tools;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import watchgirl.dataObjects.SignalOutput;

public class HmacColorMapperTest {

    private static final String DIGEST_FIRST_63_DIGITS =
            "000000000000000000000000000000000000000000000000000000000000000";

    @ParameterizedTest
    @ValueSource(strings = {"0", "4", "8", "c"})
    void getSignal_givenInputMod4Equals0_returnsRed(String value) {
        SignalOutput actual = HmacColorMapper.getSignal(DIGEST_FIRST_63_DIGITS + value);

        Assertions.assertEquals(SignalOutput.RED, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "5", "9", "d"})
    void getSignal_givenInputMod4Equals0_returnsGreen(String value) {
        SignalOutput actual = HmacColorMapper.getSignal(DIGEST_FIRST_63_DIGITS + value);

        Assertions.assertEquals(SignalOutput.GREEN, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {"2", "6", "a", "e"})
    void getSignal_givenInputMod4Equals0_returnsBlue(String value) {
        SignalOutput actual = HmacColorMapper.getSignal(DIGEST_FIRST_63_DIGITS + value);

        Assertions.assertEquals(SignalOutput.BLUE, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {"3", "7", "b", "f"})
    void getSignal_givenInputMod4Equals0_returnsWhite(String value) {
        SignalOutput actual = HmacColorMapper.getSignal(DIGEST_FIRST_63_DIGITS + value);

        Assertions.assertEquals(SignalOutput.WHITE, actual);
    }
}
