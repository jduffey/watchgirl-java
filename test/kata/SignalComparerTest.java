package kata;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.mockito.Mockito.when;

import static org.mockito.Mockito.mock;

class SignalComparerTest {

//    private final SignalMaker signalMaker = mock(SignalMaker.class);
//    private final String SIGNAL_OUTPUT = "SIGNAL_OUTPUT";
    private SignalComparer signalComparer;

    @BeforeEach
    void setup() {
        signalComparer = new SignalComparer();
    }

    @Test
    void compareSignal_givenExpectedSignal_returnsTrue() {
        SignalOutput receivedSignal = SignalOutput.RED;
        SignalOutput expectedSignal = SignalOutput.RED;

        boolean actual = signalComparer.compareSignal(receivedSignal, expectedSignal);

        Assertions.assertTrue(actual);
    }

    @ParameterizedTest
    @EnumSource(value = SignalOutput.class, names = {"GREEN", "BLUE", "WHITE"})
    void compareSignal_givenUnexpectedSignal_returnsFalse(SignalOutput receivedSignal) {
        SignalOutput expectedSignal = SignalOutput.RED;

        boolean actual = signalComparer.compareSignal(receivedSignal, expectedSignal);

        Assertions.assertFalse(actual);
    }
}
