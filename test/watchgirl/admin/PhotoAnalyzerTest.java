package watchgirl.admin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import watchgirl.dataObjects.AnalyzedPhotograph;
import watchgirl.dataObjects.Photograph;
import watchgirl.dataObjects.AnalyzedPhotographStatus;
import watchgirl.dataObjects.SignalOutput;
import watchgirl.tools.HmacGenerator;

import java.util.UUID;

import static org.mockito.Mockito.*;

class PhotoAnalyzerTest {

    private static final SignalOutput EXPECTED_SIGNAL = SignalOutput.RED;
    private static final String TIME = "TIME";
    private static final String SECRET = "SECRET";
    private static final HmacGenerator hmacGenerator = mock(HmacGenerator.class);
    private static final Photograph photograph = mock(Photograph.class);
    private static final SecretKeeper secretKeeper = mock(SecretKeeper.class);
    private static final String HMAC_FIRST_63_DIGITS =
            "000000000000000000000000000000000000000000000000000000000000000";
    private PhotoAnalyzer underTest;

    @BeforeEach
    void setup() {
        underTest = new PhotoAnalyzer(hmacGenerator, secretKeeper);
        UUID cameraId = UUID.randomUUID();

        when(photograph.getCameraId()).thenReturn(cameraId);
        when(photograph.getTime()).thenReturn(TIME);
        when(photograph.getSignal()).thenReturn(EXPECTED_SIGNAL);

        when(secretKeeper.getSecret(cameraId)).thenReturn(SECRET);

        reset(hmacGenerator);
    }

    @Test
    void createAnalyzedPhotograph_statusOk() throws Exception {
        when(hmacGenerator.generateHmac(TIME, SECRET)).thenReturn(HMAC_FIRST_63_DIGITS + "0");

        AnalyzedPhotograph actual = underTest.createAnalyzedPhotograph(photograph);

        Assertions.assertEquals(AnalyzedPhotographStatus.MATCH, actual.getStatus());
    }

    @Test
    void createAnalyzedPhotograph_statusBad() throws Exception {
        when(hmacGenerator.generateHmac(TIME, SECRET)).thenReturn(HMAC_FIRST_63_DIGITS + "1");

        AnalyzedPhotograph actual = underTest.createAnalyzedPhotograph(photograph);

        Assertions.assertEquals(AnalyzedPhotographStatus.NO_MATCH, actual.getStatus());
    }

    @Test
    void createAnalyzedPhotograph_whenPhotoSignalIsError_marksPhotoStatusAsSignalError() throws Exception {
        String valid64DigitHmac = "0000000000000000000000000000000000000000000000000000000000000000";
        when(hmacGenerator.generateHmac(TIME, SECRET)).thenReturn(valid64DigitHmac);
        when(photograph.getSignal()).thenReturn(SignalOutput.SIGNAL_ERROR);

        AnalyzedPhotograph actual = underTest.createAnalyzedPhotograph(photograph);

        Assertions.assertEquals(AnalyzedPhotographStatus.SIGNAL_ERROR, actual.getStatus());
    }

    @Test
    void createAnalyzedPhotograph_throwsException_marksPhotoStatusAsAnalyzeError() throws Exception {
        doThrow(new Exception()).when(hmacGenerator).generateHmac(TIME, SECRET);

        AnalyzedPhotograph actual = underTest.createAnalyzedPhotograph(photograph);

        Assertions.assertEquals(AnalyzedPhotographStatus.ANALYZE_ERROR, actual.getStatus());
    }
}
