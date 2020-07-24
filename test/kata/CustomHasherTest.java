package kata;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CustomHasherTest {

    @Test
    void getDigest_givenTimeAndSecret_returnsDigest() {
        CustomHasher underTest = new CustomHasher();

        Assertions.assertEquals(
                "454d4cfcdc208b174a3b3043df1df29a078413095f1f89ebe80475f4640596f9",
                underTest.getDigest("CURRENT_TIME", "SECRET"));
    }
}
